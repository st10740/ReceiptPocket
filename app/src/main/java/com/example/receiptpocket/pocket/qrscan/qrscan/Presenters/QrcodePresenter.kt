package com.example.receiptpocket.pocket.qrscan.qrscan.Presenters

import android.app.Activity
import com.example.receiptpocket.Room.Receipt

interface QrcodePresenter {
    fun analyzeScanStr(str: String)
    fun checkCameraPermission(activity: Activity, permission: String, requestCode: Int)
    fun storeCodeData(receipt: Receipt)
}