package com.example.receiptpocket.pocket.qrscan.qrscan.Interactors

import com.example.receiptpocket.pocket.qrscan.qrscan.Listeners.OnQrcodeScanFinishedListener

class QrcodeInteractorImpl: QrcodeInteractor {
    override fun analyzeQrcodeStr(str: String, listener: OnQrcodeScanFinishedListener) {
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

                listener.analyzeSuccess("", year, month, day, code_1, code_2, price, "")
            }
            else{
                listener.analyzeFail()
            }

        }
        else{
            listener.analyzeFail()
        }
    }

    private fun isUppercase(char: Char): Boolean{
        if(char in 'A'..'Z') return true
        return false
    }

    private fun isNumber(char: Char): Boolean{
        if(char in '0'..'9') return true
        return false
    }
}