package com.example.receiptpocket.login.Interactors

import com.example.receiptpocket.MysqlCon
import com.example.receiptpocket.login.Listeners.OnLoginFinishedListener

class LoginInteractorImpl : LoginInteractor {
    override fun login(username: String, password: String, listener: OnLoginFinishedListener) {
        Thread( Runnable {

            if ((!username.equals("")) && (!password.equals(""))){

                val con = MysqlCon()
                con.run()
                val dataList = con.getAccount(username) // id, name, password

                if (dataList.isEmpty()) { listener.setErrorUsername() }

                else if (!password.equals(dataList.get(2))) { listener.setErrorPassword() }

                else { listener.onSuccess(dataList.get(0), dataList.get(1), dataList.get(2)) }
            }


            if(username.equals("")) { listener.setEmptyUsername() }

            if (password.equals("")) { listener.setEmptyPassword() }



        }).start()
    }
}