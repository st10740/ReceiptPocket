package com.example.receiptpocket.pocket.qrscan.manualInput.Listeners

interface OnUpdateDataFinshedListener {
    fun onUpdateDataSuccess()
    fun setCode1CharNumError()
    fun setCode1UppercaseError()
    fun setCode2CharNumError()
}