/* Class to implement SharedPreferences for
 earliest year & month easily */


package com.example.receiptpocket

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {
    private val SHARE = "SHARE"
    private val EARLIEST_YEAR = "EARLIST_YEAR"
    private val EARLIEST_MONTH = "EARLIEST_MONTH"
    private val EARLIEST_DAY = "EARLIEST_DAY"

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

}