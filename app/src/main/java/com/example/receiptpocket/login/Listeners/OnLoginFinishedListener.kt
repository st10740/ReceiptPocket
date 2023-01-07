package com.example.receiptpocket.login.Listeners

interface OnLoginFinishedListener {
    fun onSuccess(id: String, name: String, password: String)
    fun setErrorUsername()
    fun setErrorPassword()
    fun setEmptyUsername()
    fun setEmptyPassword()
}