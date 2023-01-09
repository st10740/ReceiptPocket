package com.example.receiptpocket.pocket.receipts.Views

import com.example.receiptpocket.Room.Receipt
import com.example.receiptpocket.pocket.receipts.ReceiptItem

interface ReceiptView {
    fun showProgressBar()
    fun hindProgressBar()
    fun initRecycler(list: List<ReceiptItem>)
    fun navigateToManualWithData(data: Receipt)
}