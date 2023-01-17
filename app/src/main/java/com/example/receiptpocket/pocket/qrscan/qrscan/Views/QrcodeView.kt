package com.example.receiptpocket.pocket.qrscan.qrscan.Views

interface QrcodeView {
    fun showDialog(store: String, year: String, month: String, day: String,
                            code_1: String, code_2: String, price: String, describes: String)
    fun reloadQrcodeScanner()
    fun navigateToPocket()
}