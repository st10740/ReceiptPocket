package com.example.receiptpocket.pocket.receipts.Interactors

import com.example.receiptpocket.pocket.receipts.Listeners.OnReceiptItemGetListener
import java.time.Month
import java.time.Year

interface ReceiptInteractor {
    fun getMonthItems(year: Int, month: Int, listener: OnReceiptItemGetListener)
    fun getDateItems(year: Int, month: Int, day: Int, listener: OnReceiptItemGetListener)
    fun getItem(id: String, code_1: String, code_2: String, listener: OnReceiptItemGetListener)
}