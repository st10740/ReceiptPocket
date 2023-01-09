package com.example.receiptpocket.pocket.account.Interactors

import android.util.Log
import com.example.receiptpocket.App
import com.example.receiptpocket.MysqlCon
import com.example.receiptpocket.ReceiptModel
import com.example.receiptpocket.Room.ReceiptDatabase
import com.example.receiptpocket.pocket.account.Listeners.OnLogoutFinishedListener
import com.example.receiptpocket.prefs

class AccountInteractorImpl : AccountInteractor {
    override fun logout(listener: OnLogoutFinishedListener) {
        Thread{

            val con = MysqlCon()
            con.run()

            // 刪除遠端資料庫目前登入者的所有Receipt資料
            con.deleteReceiptsById(prefs.userNamePrefs!!)

            val receiptDao = ReceiptDatabase.getDatabase(App.appContext).receiptDao()
            val curUserReceipts = receiptDao.getAll(prefs.userNamePrefs!!)
            val receiptList = mutableListOf<ReceiptModel>()

            println("get all finished")
            for(item in curUserReceipts){
                println("enter item: ${item}")
                receiptList.add(
                    ReceiptModel(item.sid, item.store, item.year, item.month, item.day,
                item.code_1, item.code_2, item.price, item.describes)
                )
            }

            println(receiptList)

            con.insertReceipts(receiptList)


            // SharedPreference 的 username, name, password 歸零
            prefs.userNamePrefs = ""
            prefs.namePrefs = ""
            prefs.passwordPrefs = ""

            listener.onSuccess()

        }.start()
    }
}