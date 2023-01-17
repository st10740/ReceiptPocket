package com.example.receiptpocket.pocket.qrscan.qrscan.Presenters

import com.example.receiptpocket.pocket.qrscan.qrscan.Interactors.QrcodeInteractor
import com.example.receiptpocket.pocket.qrscan.qrscan.Interactors.QrcodeInteractorImpl
import com.example.receiptpocket.pocket.qrscan.qrscan.Listeners.OnQrcodeScanFinishedListener
import com.example.receiptpocket.pocket.qrscan.qrscan.Views.QrcodeView

class QrcodePresenterImpl(val qrcodeView: QrcodeView): QrcodePresenter, OnQrcodeScanFinishedListener {
    val qrcodeInteractor: QrcodeInteractor

    init {
        qrcodeInteractor = QrcodeInteractorImpl()
    }

    override fun analyzeScanStr(str: String) {
        qrcodeInteractor.analyzeQrcodeStr(str, this)

    }

    override fun analyzeSuccess(
        store: String,
        year: String,
        month: String,
        day: String,
        code_1: String,
        code_2: String,
        price: String,
        describes: String
    ) {
        qrcodeView.navigateManualInput(store, year, month, day, code_1, code_2, price, describes)
    }

    override fun analyzeFail() {
        qrcodeView.reloadQrcodeScanner()
    }

}