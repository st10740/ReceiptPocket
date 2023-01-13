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

    override fun setCode1CharNumError() {
        view.hideProgress()
        view.setCode1CharNumError()
    }

    override fun setCode1UppercaseError() {
        view.hideProgress()
        view.setCode1UppercaseError()
    }

    override fun setCode2CharNumError() {
        view.hideProgress()
        view.setCode2CharNumError()
    }

    override fun onUpdateDataSuccess() {
        view.hideProgress()
        view.navigateToPocket()
    }
}