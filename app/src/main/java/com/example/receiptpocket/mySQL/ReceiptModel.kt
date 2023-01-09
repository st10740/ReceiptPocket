package com.example.receiptpocket.mySQL

data class ReceiptModel(
    val sid: String,
    val store: String?,
    val year: Int,
    val month: Int,
    val day: Int,
    val code_1: String,
    val code_2: String,
    val price: Int?,
    val describes: String?
)
