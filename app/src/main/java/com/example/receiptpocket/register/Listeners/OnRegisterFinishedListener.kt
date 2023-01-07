package com.example.receiptpocket.register.Listeners

interface OnRegisterFinishedListener {
    fun onSuccess()
    fun setRepeatedUsername()
    fun setEmptyUsername()
    fun setEmptyName()
    fun setEmptyPassword()
}