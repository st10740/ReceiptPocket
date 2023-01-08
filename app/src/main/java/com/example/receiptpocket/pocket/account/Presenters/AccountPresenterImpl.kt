package com.example.receiptpocket.pocket.account.Presenters

import com.example.receiptpocket.pocket.account.Interactors.AccountInteractor
import com.example.receiptpocket.pocket.account.Interactors.AccountInteractorImpl
import com.example.receiptpocket.pocket.account.Listeners.OnLogoutFinishedListener
import com.example.receiptpocket.pocket.account.Views.AccountView

class AccountPresenterImpl(val accView: AccountView): AccountPresenter, OnLogoutFinishedListener {
    val accountIterator: AccountInteractor

    init {
        accountIterator = AccountInteractorImpl()
    }

    override fun logout() {
        accView.showProgress()
        accountIterator.logout(this)
    }

    override fun onSuccess() {
        accView.hideProgress()
        accView.navigateToLogin()
    }


}