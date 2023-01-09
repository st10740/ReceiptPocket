package com.example.receiptpocket.pocket.qrscan.manualInput.Presenters

import com.example.receiptpocket.Room.Receipt

interface ManualInputPresenter {
    fun doUpdateItem(item: Receipt, isDelete: Boolean)
}