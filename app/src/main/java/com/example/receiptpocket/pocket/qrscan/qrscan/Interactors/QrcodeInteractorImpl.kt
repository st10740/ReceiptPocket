package com.example.receiptpocket.pocket.qrscan.qrscan.Interactors

import android.app.Activity
import android.content.pm.PackageManager
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.receiptpocket.App
import com.example.receiptpocket.Room.Receipt
import com.example.receiptpocket.Room.ReceiptDatabase
import com.example.receiptpocket.pocket.qrscan.qrscan.Listeners.OnQrcodeScanFinishedListener
import java.sql.SQLException

class QrcodeInteractorImpl: QrcodeInteractor {


    override fun analyzeQrcodeStr(string: String, listener: OnQrcodeScanFinishedListener) {
        val str = string.trimEnd()
        if(str.length > 10){
            var isCodeFlag = true
            for(i in 0..1) { if(!isUppercase(str[i])) isCodeFlag=false; break }
            for(j in 2..9) { if(!isNumber(str[j])) isCodeFlag=false; break}

            if(isCodeFlag){
                val year = str.substring(10, 13).trimStart('0')
                val month = str.substring(13, 15).trimStart('0')
                val day = str.substring(15, 17).trimStart('0')
                val code_1 = str.substring(0,2)
                val code_2 = str.substring(2,10)
                val price = (Integer.decode("0x" + str.substring(29, 37))).toString().trimStart('0')
                var describes = ""

                Log.d("code", "length: ${str.length}")
                if(str.length > 89){
                    val productStrList = getSplitProductsAndPriceList(str.substring(89, str.length))  // 營業人自行使用區 + ':' 後的字串皆被丟進去
                    for(item in productStrList){
                        Log.d("code", item)
                        describes+=item
                        describes+="\n"
                    }
                }


                listener.analyzeSuccess("", year, month, day, code_1, code_2, price, describes)
            }
            else{
                listener.analyzeFail()
            }

        }
        else{
            listener.analyzeFail()
        }
    }

    override fun checkCameraPermission(
        activity: Activity,
        permission: String,
        requestCode: Int,
        listener: OnQrcodeScanFinishedListener
    ) {
        if(ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(activity, arrayOf(permission), requestCode)
        }
    }

    override fun storeCodeData(receipt: Receipt, listener: OnQrcodeScanFinishedListener) {
        Thread{
            try {
                val receiptDao = ReceiptDatabase.getDatabase(App.appContext).receiptDao()
                receiptDao.insert(receipt)
                listener.storeSuccess()

            } catch (e: SQLException){
                e.printStackTrace()
            }
        }.start()
    }

    private fun isUppercase(char: Char): Boolean{
        if(char in 'A'..'Z') return true
        return false
    }

    private fun isNumber(char: Char): Boolean{
        if(char in '0'..'9') return true
        return false
    }

    private fun getSplitProductsAndPriceList(str: String): List<String>{
        val strTrim = str.trimEnd(':')
        val productStrList = mutableListOf<String>()
        val splitStrList = strTrim.split(':')

        Log.d("code", "$splitStrList")

        for(i in 0 until splitStrList.size step 3){
            if(i>=splitStrList.size || (i+1)>=splitStrList.size || (i+2)>=splitStrList.size){  //解決某些發票Qrcode獲取字串殘破問題，若遇到就不讀明細
                break
            }

            if(i!=0){  //排除 二維條碼記載完整品目筆數, 該張發票交易品目總筆數, 中文編碼參數三個數
                val item = splitStrList[i] + " $" + splitStrList[i+2] + "x" + splitStrList[i+1]
                productStrList.add(item)
            }
        }


        return productStrList
    }


}