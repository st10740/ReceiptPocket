package com.example.receiptpocket.pocket.account.Interactors

import com.example.receiptpocket.pocket.account.Listeners.OnLogoutFinishedListener

interface AccountInteractor {
    fun logout(listener: OnLogoutFinishedListener)
}