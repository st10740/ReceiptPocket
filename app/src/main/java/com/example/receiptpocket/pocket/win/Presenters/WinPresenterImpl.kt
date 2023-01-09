package com.example.receiptpocket.pocket.win.Presenters

import com.example.receiptpocket.Room.Receipt
import com.example.receiptpocket.pocket.win.Interactors.WinInteractor
import com.example.receiptpocket.pocket.win.Interactors.WinInteractorImpl
import com.example.receiptpocket.pocket.win.Listeners.OnWinReceiptGetListener
import com.example.receiptpocket.pocket.win.ReceiptWinItem
import com.example.receiptpocket.pocket.win.Views.WinView

class WinPresenterImpl(val view: WinView): WinPresenter, OnWinReceiptGetListener {

    val winInteractor: WinInteractor

    init {
        winInteractor = WinInteractorImpl()
    }

    override fun validateWin(
        year: String,
        month: String,
        first: String,
        second: String,
        third: String,
        four: String,
        fif: String
    ) {
        view.showProgressBar()
        winInteractor.checkWin(year, month, first, second, third, four, fif, this)
    }

    override fun getOneItem(id: String, code_1: String, code_2: String, prize: String) {
        view.showProgressBar()
        winInteractor.loadOneItem(id, code_1, code_2, prize,this)
    }


    override fun getWinItemSuccess(list: List<ReceiptWinItem>) {
        view.hindProgressBar()
        view.initRecycler(list)
    }

    override fun getOneItemSuccess(item: Receipt, prize: String) {
        view.hindProgressBar()
        view.navigateToWinDetailWithData(item, prize)
    }

}