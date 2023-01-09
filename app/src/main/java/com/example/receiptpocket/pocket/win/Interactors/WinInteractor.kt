package com.example.receiptpocket.pocket.win.Interactors

import com.example.receiptpocket.pocket.win.Listeners.OnWinReceiptGetListener

interface WinInteractor {
    fun checkWin(year: String, month: String, first: String, second: String,
                    third: String, four: String, fif: String, listener: OnWinReceiptGetListener)
    fun loadOneItem(id: String, code_1: String, code_2: String, prize: String, listener: OnWinReceiptGetListener)
}