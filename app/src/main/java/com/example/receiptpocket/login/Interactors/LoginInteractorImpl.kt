package com.example.receiptpocket.login.Interactors

import com.example.receiptpocket.MysqlCon
import com.example.receiptpocket.login.Listeners.OnLoginFinishedListener

class LoginInteractorImpl : LoginInteractor {
    override fun login(username: String, password: String, listener: OnLoginFinishedListener) {
        Thread( Runnable {
            val con = MysqlCon()
            con.run()
            val dataList = con.getAccount(username) // id, name, password

            if(username.equals("")) { listener.setEmptyUsername() }

            else if (password.equals("")) { listener.setEmptyPassword() }

            else if (dataList.isEmpty()) { listener.setErrorUsername() }

            else if (!password.equals(dataList.get(2))) { listener.setErrorPassword() }

            else { listener.onSuccess(dataList.get(0), dataList.get(1), dataList.get(2)) }

        }).start()
    }
}