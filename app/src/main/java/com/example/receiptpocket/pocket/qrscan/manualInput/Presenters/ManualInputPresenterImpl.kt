package com.example.receiptpocket.pocket.qrscan.manualInput.Presenters

import com.example.receiptpocket.Room.Receipt
import com.example.receiptpocket.pocket.qrscan.manualInput.Interactors.ManualInputInteractor
import com.example.receiptpocket.pocket.qrscan.manualInput.Interactors.ManualInputInteractorImpl
import com.example.receiptpocket.pocket.qrscan.manualInput.Listeners.OnUpdateDataFinshedListener
import com.example.receiptpocket.pocket.qrscan.manualInput.Views.ManualInputView
import com.example.receiptpocket.prefs

class ManualInputPresenterImpl(val view: ManualInputView): ManualInputPresenter, OnUpdateDataFinshedListener {
    val manualInputInteractor: ManualInputInteractor

    init {
        manualInputInteractor = ManualInputInteractorImpl()
    }


    override fun doUpdateItem(item: Receipt, isDelete: Boolean) {
        view.showProgress()
        manualInputInteractor.updateItem(item, isDelete, this)
    }

    override fun onUpdateDataSuccess() {
        view.hideProgress()
        view.navigateToPocket()
    }
}