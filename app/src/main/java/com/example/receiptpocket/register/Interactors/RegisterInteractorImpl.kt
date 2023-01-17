package com.example.receiptpocket.register.Interactors


import com.example.receiptpocket.mySQL.MysqlFlaskCon
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

                val con = MysqlFlaskCon()
                val repeatedUsername = con.getAccount(id)

                if(repeatedUsername[0].equals("")){ // 沒有重複的帳號名稱
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