package com.example.receiptpocket.pocket.win.Listeners

import com.example.receiptpocket.Room.Receipt
import com.example.receiptpocket.pocket.win.ReceiptWinItem

interface OnWinReceiptGetListener {
    fun getWinItemSuccess(list: List<ReceiptWinItem>)
    fun getOneItemSuccess(item: Receipt, prize: String)
}