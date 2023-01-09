package com.example.receiptpocket.pocket.qrscan.manualInput.Interactors

import com.example.receiptpocket.Room.Receipt
import com.example.receiptpocket.pocket.qrscan.manualInput.Listeners.OnUpdateDataFinshedListener

interface ManualInputInteractor {
    fun updateItem(item: Receipt, isDelete: Boolean, listener: OnUpdateDataFinshedListener)
}