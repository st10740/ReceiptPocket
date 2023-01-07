package com.example.receiptpocket.login.Interactors

import com.example.receiptpocket.login.Listeners.OnLoginFinishedListener

interface LoginInteractor {
    fun login(username: String, password: String, listener: OnLoginFinishedListener)

}