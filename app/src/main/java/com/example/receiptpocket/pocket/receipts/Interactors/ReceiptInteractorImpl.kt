package com.example.receiptpocket.pocket.receipts.Interactors

import android.util.Log
import com.example.receiptpocket.App
import com.example.receiptpocket.Room.ReceiptDatabase
import com.example.receiptpocket.pocket.receipts.Listeners.OnReceiptItemGetListener
import com.example.receiptpocket.pocket.receipts.ReceiptItem
import com.example.receiptpocket.prefs
import java.sql.SQLException

class ReceiptInteractorImpl : ReceiptInteractor {
    override fun getMonthItems(year: Int, month: Int, listener: OnReceiptItemGetListener) {
        Thread{
            try {
                val itemList = mutableListOf<ReceiptItem>()

                val receiptDao = ReceiptDatabase.getDatabase(App.appContext).receiptDao()
                val monthReceipts = receiptDao.findByMonth(prefs.userNamePrefs!!, year, month)

                for(item in monthReceipts){
                    val date = item.month.toString() + "/" + item.day.toString()
                    val store = item.store ?: ""
                    val code = item.code_1 + "-" + item.code_2
                    val price = (item.price)?.toString() ?: ""

                    println("date: $date, store: $store, code: $code, price: $price")

                    itemList.add(ReceiptItem(date, store, code, price))
                }

                listener.getItemSuccess(itemList)


            } catch (e: SQLException){
                e.printStackTrace()
            }
        }.start()
    }

    override fun getDateItems(year: Int, month: Int, day: Int, listener: OnReceiptItemGetListener) {
        Thread{
            try {
                val itemList = mutableListOf<ReceiptItem>()

                val receiptDao = ReceiptDatabase.getDatabase(App.appContext).receiptDao()
                val monthReceipts = receiptDao.findByDate(prefs.userNamePrefs!!, year, month, day)

                for(item in monthReceipts){
                    val date = item.month.toString() + "/" + item.day.toString()
                    val store = item.store ?: ""
                    val code = item.code_1 + "-" + item.code_2
                    val price = (item.price)?.toString() ?: ""

                    itemList.add(ReceiptItem(date, store, code, price))
                }

                listener.getItemSuccess(itemList)
                Log.v("DB", "ReceiptDate interator!!!")

            } catch (e: SQLException){
                e.printStackTrace()
            }
        }.start()
    }

    override fun getItem(
        id: String,
        code_1: String,
        code_2: String,
        listener: OnReceiptItemGetListener
    ) {
        Thread{
            try {
                val receiptDao = ReceiptDatabase.getDatabase(App.appContext).receiptDao()
                val receiptItem = receiptDao.getOneItem(id, code_1, code_2)
                listener.getOneReceiptSuccess(receiptItem)

            } catch (e: SQLException){
                e.printStackTrace()
            }

        }.start()
    }
}