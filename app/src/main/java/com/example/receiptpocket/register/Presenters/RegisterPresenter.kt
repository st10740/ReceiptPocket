package com.example.receiptpocket.register.Presenters

interface RegisterPresenter {
    fun validateRegister(id: String, name: String, password: String)
}