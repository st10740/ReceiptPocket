package com.example.receiptpocket.pocket.qrscan.qrscan.Listeners

interface OnQrcodeScanFinishedListener {
    fun analyzeSuccess(store: String, year: String, month: String, day: String,
                       code_1: String, code_2: String, price: String, describes: String)
    fun analyzeFail()
    fun storeSuccess()
}