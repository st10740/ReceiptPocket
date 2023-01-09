package com.example.receiptpocket.login.Interactors

import android.util.Log
import com.example.receiptpocket.App
import com.example.receiptpocket.MysqlCon
import com.example.receiptpocket.Room.Receipt
import com.example.receiptpocket.Room.ReceiptDatabase
import com.example.receiptpocket.login.Listeners.OnLoginFinishedListener
import com.example.receiptpocket.prefs

class LoginInteractorImpl : LoginInteractor {
    override fun login(username: String, password: String, listener: OnLoginFinishedListener) {
        Thread{

            if ((!username.equals("")) && (!password.equals(""))){

                val con = MysqlCon()
                con.run()
                val dataList = con.getAccount(username) // id, name, password

                if (dataList.isEmpty()) { listener.setErrorUsername() }

                else if (!password.equals(dataList.get(2))) { listener.setErrorPassword() }

                else { // success

                    val receiptDao = ReceiptDatabase.getDatabase(App.appContext).receiptDao()
                    val receiptsList = con.getReceipts(username)

                    receiptDao.deleteAll() // 先刪除本機資料庫Receipt Table中的全部rows

                    println("#######################")
                    for(item in receiptsList){ // 再把遠端資料庫的資料全部insert到本機資料庫Receipt Table
                        receiptDao.insert(Receipt(sid = item.sid, store = item.store, year = item.year,
                        month = item.month, day = item.day, code_1 = item.code_1, code_2 = item.code_2,
                        price = item.price, describes = item.describes))

                        println("@@@@@@@@@@@@@@@@@")
                        println(item)
                    }

                    prefs.userNamePrefs = dataList[0]
                    prefs.namePrefs = dataList[1]
                    prefs.passwordPrefs = dataList[2]

                    listener.onSuccess()
                }
            }


            if(username.equals("")) { listener.setEmptyUsername() }

            if (password.equals("")) { listener.setEmptyPassword() }


        }.start()
    }
}