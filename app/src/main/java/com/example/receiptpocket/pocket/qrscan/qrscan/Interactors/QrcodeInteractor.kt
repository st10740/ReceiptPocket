package com.example.receiptpocket.pocket.qrscan.qrscan.Interactors

import android.app.Activity
import com.example.receiptpocket.Room.Receipt
import com.example.receiptpocket.pocket.qrscan.qrscan.Listeners.OnQrcodeScanFinishedListener

interface QrcodeInteractor {
    fun analyzeQrcodeStr(string: String, listener: OnQrcodeScanFinishedListener)
    fun checkCameraPermission(activity: Activity, permission: String, requestCode: Int, listener: OnQrcodeScanFinishedListener)
    fun storeCodeData(receipt: Receipt, listener: OnQrcodeScanFinishedListener)
}