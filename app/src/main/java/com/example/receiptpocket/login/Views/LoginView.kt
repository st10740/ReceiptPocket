package com.example.receiptpocket.login.Views

interface LoginView {
    fun showProgress()
    fun hideProgress()
    fun setErrorUserName()
    fun setErrorPassword()
    fun setErrorEmptyUserName()
    fun setErrorEmptyPassword()
    fun navigateToPocket()
}