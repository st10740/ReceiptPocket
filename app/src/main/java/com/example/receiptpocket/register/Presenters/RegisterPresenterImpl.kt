package com.example.receiptpocket.register.Presenters

import com.example.receiptpocket.register.Interactors.RegisterInteractor
import com.example.receiptpocket.register.Interactors.RegisterInteractorImpl
import com.example.receiptpocket.register.Listeners.OnRegisterFinishedListener
import com.example.receiptpocket.register.Views.RegisterView

class RegisterPresenterImpl(val registerView: RegisterView): RegisterPresenter, OnRegisterFinishedListener {

    val registerInteractor: RegisterInteractor

    init {
        registerInteractor = RegisterInteractorImpl()
    }

    override fun validateRegister(id: String, name: String, password: String) {
        registerView.showProgress()
        registerInteractor.register(id, name, password, this)
    }

    override fun onSuccess() {
        registerView.hideProgress()
        registerView.navigateToLogin()
    }

    override fun setRepeatedUsername() {
        registerView.hideProgress()
        registerView.setErrorRepeatedId()
    }

    override fun setEmptyUsername() {
        registerView.hideProgress()
        registerView.setErrorEmptyId()
    }

    override fun setEmptyName() {
        registerView.hideProgress()
        registerView.setErrorEmptyName()
    }

    override fun setEmptyPassword() {
        registerView.hideProgress()
        registerView.setErrorEmptyPassword()
    }


}