package com.example.receiptpocket.Room

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["sid", "code_1", "code_2"])
data class Receipt(

    val sid: String,
    val store: String?,
    @NonNull val year: Int,
    @NonNull val month: Int,
    @NonNull val day: Int,
    val code_1: String,
    val code_2: String,
    val price: Int?,
    val describes: String?
)
