package com.example.receiptpocket.pocket.qrscan.manualInput.Interactors

import com.example.receiptpocket.App
import com.example.receiptpocket.Room.Receipt
import com.example.receiptpocket.Room.ReceiptDatabase
import com.example.receiptpocket.pocket.qrscan.manualInput.Listeners.OnUpdateDataFinshedListener
import java.sql.SQLException

class ManualInputInteractorImpl: ManualInputInteractor {
    override fun updateItem(
        item: Receipt,
        isDelete: Boolean,
        listener: OnUpdateDataFinshedListener
    ) {
        Thread{

            // 確認輸入欄位資料符合規定
            if(isCode1TwoChar(item.code_1) && isCode1Uppercase(item.code_1) && isCode2EightChar(item.code_2)){
                try {
                    val receiptDao = ReceiptDatabase.getDatabase(App.appContext).receiptDao()

                    if (isDelete) { receiptDao.delete(item) }
                    else { receiptDao.insert(item) }

                    listener.onUpdateDataSuccess()

                } catch (e: SQLException){
                    e.printStackTrace()
                }
            }
            else{

                if (!isCode1TwoChar(item.code_1)) { listener.setCode1CharNumError() }
                else if (!isCode1Uppercase(item.code_1)) { listener.setCode1UppercaseError() }
                else { listener.setCode2CharNumError() }
            }

        }.start()

    }


    private fun isCode1TwoChar(str: String): Boolean {
        if(str.length==2) return true
        return false
    }

    private fun isCode2EightChar(str: String): Boolean {
        if(str.length==8) return true
        return false
    }

    private fun isCode1Uppercase(str: String): Boolean {
        if(str[0] in 'A'..'Z' && str[1] in 'A'..'Z') return true
        return false
    }

}