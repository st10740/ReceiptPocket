package com.example.receiptpocket.login.Presenters

interface LoginPresenter {
    fun validateLogin(username: String, password: String)
}