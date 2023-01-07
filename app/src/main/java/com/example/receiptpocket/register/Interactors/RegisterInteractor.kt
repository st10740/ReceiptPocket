package com.example.receiptpocket.register.Interactors

import com.example.receiptpocket.register.Listeners.OnRegisterFinishedListener

interface RegisterInteractor {
    fun register(id: String, name: String, password: String, listener: OnRegisterFinishedListener)
}