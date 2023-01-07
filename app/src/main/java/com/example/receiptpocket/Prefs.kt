/* Class to implement SharedPreferences for
 earliest year & month easily */


package com.example.receiptpocket

import android.content.Context
import android.content.SharedPreferences
import android.icu.lang.UCharacter.IndicPositionalCategory.NA

class Prefs(context: Context) {
    private val SHARE = "SHARE"
    private val EARLIEST_YEAR = "EARLIST_YEAR"
    private val EARLIEST_MONTH = "EARLIEST_MONTH"
    private val EARLIEST_DAY = "EARLIEST_DAY"
    private val USER_NAME = "USER_NAME"
    private val PASSWORD = "PASSWORD"
    private val NAME = "NAME"

    private val preferences:SharedPreferences = context.getSharedPreferences(SHARE, Context.MODE_PRIVATE)


    var yearPref: Int
        get() = preferences.getInt(EARLIEST_YEAR, 100)
        set(value) = preferences.edit().putInt(EARLIEST_YEAR, value).apply()

    var monthPref: Int
        get() = preferences.getInt(EARLIEST_MONTH, 1)
        set(value) = preferences.edit().putInt(EARLIEST_MONTH, value).apply()

    var dayPrefs: Int
        get() = preferences.getInt(EARLIEST_DAY, 1)
        set(value) = preferences.edit().putInt(EARLIEST_DAY, value).apply()

    var userNamePrefs: String?
        get() = preferences.getString(USER_NAME, "")
        set(value) = preferences.edit().putString(USER_NAME, value).apply()

    var passwordPrefs: String?
        get() = preferences.getString(PASSWORD, "")
        set(value) = preferences.edit().putString(PASSWORD, value).apply()

    var namePrefs: String?
        get() = preferences.getString(NAME, "")
        set(value) = preferences.edit().putString(NAME, value).apply()

}