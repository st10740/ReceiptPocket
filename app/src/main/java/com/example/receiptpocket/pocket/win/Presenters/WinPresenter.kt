package com.example.receiptpocket.pocket.win.Presenters

import com.example.receiptpocket.Room.Receipt


interface WinPresenter {
    fun validateWin(year: String, month: String, first: String,
                    second: String, third: String, four: String, fif: String)
    fun getOneItem(id: String, code_1: String, code_2: String, prize: String)
}