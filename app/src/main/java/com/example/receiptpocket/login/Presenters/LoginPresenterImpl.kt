package com.example.receiptpocket.login.Presenters

import com.example.receiptpocket.login.Interactors.LoginInteractor
import com.example.receiptpocket.login.Interactors.LoginInteractorImpl
import com.example.receiptpocket.login.Listeners.OnLoginFinishedListener
import com.example.receiptpocket.login.Views.LoginView

class LoginPresenterImpl(val loginView: LoginView): LoginPresenter, OnLoginFinishedListener {
    val loginInteractor: LoginInteractor

    init {
        loginInteractor = LoginInteractorImpl()
    }

    // implement LoginPresenter interface
    override fun validateLogin(username: String, password: String) {
        loginView.showProgress()
        loginInteractor.login(username, password, this)
    }

    // implement OnLoginFinishedListener interface
    override fun onSuccess() {
        loginView.hideProgress()
        loginView.navigateToPocket()
    }

    override fun setErrorUsername() {
        loginView.setErrorUserName()
        loginView.hideProgress()
    }

    override fun setErrorPassword() {
        loginView.setErrorPassword()
        loginView.hideProgress()
    }

    override fun setEmptyUsername() {
        loginView.setErrorEmptyUserName()
        loginView.hideProgress()
    }

    override fun setEmptyPassword() {
        loginView.setErrorEmptyPassword()
        loginView.hideProgress()
    }


}