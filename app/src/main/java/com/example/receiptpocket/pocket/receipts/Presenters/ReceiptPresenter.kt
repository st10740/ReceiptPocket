package com.example.receiptpocket.pocket.receipts.Presenters

import java.time.Month
import java.time.Year

interface ReceiptPresenter {
    fun loadItems(year_: Int, month_: Int, day_: Int = 1, isMonth: Boolean = true)
    fun loadCertainItem(id: String, code_1: String, code_2: String)
}