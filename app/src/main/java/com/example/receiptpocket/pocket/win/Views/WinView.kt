package com.example.receiptpocket.pocket.win.Views

import com.example.receiptpocket.Room.Receipt
import com.example.receiptpocket.pocket.receipts.ReceiptItem
import com.example.receiptpocket.pocket.win.ReceiptWinItem

interface WinView {
    fun showProgressBar()
    fun hindProgressBar()
    fun initRecycler(list: List<ReceiptWinItem>)
    fun navigateToWinDetailWithData(data: Receipt, prize: String)
}