package com.example.receiptpocket

import android.app.Application
import android.content.Context

val prefs: Prefs by lazy { App.prefs!! }

class App: Application() {
    companion object {
        var prefs: Prefs? = null
        lateinit var instance: App
            private set

        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()

        prefs = Prefs(applicationContext)
        instance = this
        appContext = applicationContext
    }
}