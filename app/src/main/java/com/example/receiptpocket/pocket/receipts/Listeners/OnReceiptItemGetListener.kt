package com.example.receiptpocket.pocket.receipts.Listeners

import com.example.receiptpocket.Room.Receipt
import com.example.receiptpocket.pocket.receipts.ReceiptItem

interface OnReceiptItemGetListener {
    fun getItemSuccess(list: List<ReceiptItem>)
    fun getOneReceiptSuccess(item: Receipt)
}