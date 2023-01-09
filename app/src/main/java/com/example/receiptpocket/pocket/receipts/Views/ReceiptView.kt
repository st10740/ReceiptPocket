package com.example.receiptpocket.pocket.receipts.Views

import com.example.receiptpocket.pocket.receipts.ReceiptItem

interface ReceiptView {
    fun showProgressBar()
    fun hindProgressBar()
    fun initRecycler(list: List<ReceiptItem>)
}