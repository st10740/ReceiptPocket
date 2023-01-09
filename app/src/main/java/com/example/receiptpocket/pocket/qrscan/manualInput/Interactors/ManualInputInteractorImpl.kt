package com.example.receiptpocket.pocket.qrscan.manualInput.Interactors

import com.example.receiptpocket.App
import com.example.receiptpocket.Room.Receipt
import com.example.receiptpocket.Room.ReceiptDatabase
import com.example.receiptpocket.pocket.qrscan.manualInput.Listeners.OnUpdateDataFinshedListener
import java.sql.SQLException

class ManualInputInteractorImpl: ManualInputInteractor {
    override fun updateItem(
        item: Receipt,
        isDelete: Boolean,
        listener: OnUpdateDataFinshedListener
    ) {
        Thread{

            try {
                val receiptDao = ReceiptDatabase.getDatabase(App.appContext).receiptDao()

                if (isDelete) { receiptDao.delete(item) }
                else { receiptDao.insert(item) }

                listener.onUpdateDataSuccess()

            } catch (e: SQLException){
                e.printStackTrace()
            }

        }.start()

    }

}