package com.example.receiptpocket.pocket.qrscan.manualInput.Views

interface ManualInputView {
    fun showProgress()
    fun hideProgress()
    fun setCode1CharNumError()
    fun setCode1UppercaseError()
    fun setCode2CharNumError()
    fun navigateToPocket()
}