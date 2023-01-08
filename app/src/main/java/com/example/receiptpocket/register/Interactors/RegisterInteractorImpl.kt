package com.example.receiptpocket.register.Interactors

import com.example.receiptpocket.MysqlCon
import com.example.receiptpocket.register.Listeners.OnRegisterFinishedListener

class RegisterInteractorImpl : RegisterInteractor {

    override fun register(
        id: String,
        name: String,
        password: String,
        listener: OnRegisterFinishedListener
    ) {
        Thread{

            if((!id.equals("")) && (!name.equals("")) && (!password.equals(""))){

                val con = MysqlCon()
                con.run()
                val repeatedUsername = con.getAccount(id)

                if(repeatedUsername.isEmpty()){ // 沒有重複的帳號名稱
                    con.insertAccount(id, name, password)
                    listener.onSuccess()
                }
                else{
                    listener.setRepeatedUsername()
                }
            }

            if(id.equals("")) { listener.setEmptyUsername() }

            if(name.equals("")) { listener.setEmptyName() }

            if(password.equals("")) { listener.setEmptyPassword() }

        }.start()
    }
}