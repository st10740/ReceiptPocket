package com.example.receiptpocket.login.Listeners

interface OnLoginFinishedListener {
    fun onSuccess()
    fun setErrorUsername()
    fun setErrorPassword()
    fun setEmptyUsername()
    fun setEmptyPassword()
}