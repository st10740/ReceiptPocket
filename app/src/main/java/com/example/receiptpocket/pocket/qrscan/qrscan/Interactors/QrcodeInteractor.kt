package com.example.receiptpocket.pocket.qrscan.qrscan.Interactors

import com.example.receiptpocket.pocket.qrscan.qrscan.Listeners.OnQrcodeScanFinishedListener

interface QrcodeInteractor {
    fun analyzeQrcodeStr(str: String, listener: OnQrcodeScanFinishedListener)
}