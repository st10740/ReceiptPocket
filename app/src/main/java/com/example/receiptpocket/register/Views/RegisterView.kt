package com.example.receiptpocket.register.Views

interface RegisterView {
    fun showProgress()
    fun hideProgress()
    fun setErrorRepeatedId()
    fun setErrorEmptyId()
    fun setErrorEmptyName()
    fun setErrorEmptyPassword()
    fun navigateToLogin()
}