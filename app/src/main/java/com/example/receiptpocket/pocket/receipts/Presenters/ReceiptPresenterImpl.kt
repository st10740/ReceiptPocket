package com.example.receiptpocket.pocket.receipts.Presenters

import com.example.receiptpocket.pocket.receipts.Interactors.ReceiptInteractor
import com.example.receiptpocket.pocket.receipts.Interactors.ReceiptInteractorImpl
import com.example.receiptpocket.pocket.receipts.Listeners.OnReceiptItemGetListener
import com.example.receiptpocket.pocket.receipts.ReceiptItem
import com.example.receiptpocket.pocket.receipts.Views.ReceiptView

class ReceiptPresenterImpl(val view: ReceiptView): ReceiptPresenter, OnReceiptItemGetListener {
    val receiptInteractor: ReceiptInteractor
    var year: Int = 112
    var month: Int = 1
    var day: Int = 1

    init {
        receiptInteractor = ReceiptInteractorImpl()
    }


    override fun loadItems(year_: Int, month_: Int, day_: Int, isMonth: Boolean) {
        view.showProgressBar()
        if(isMonth){ receiptInteractor.getMonthItems(year_, month_,this) }
        else { receiptInteractor.getDateItems(year_, month_, day_, this) }

    }

    override fun getItemSuccess(list: List<ReceiptItem>) {
        view.hindProgressBar()
        view.initRecycler(list)
    }



}