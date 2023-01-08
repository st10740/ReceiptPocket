package com.example.receiptpocket.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Receipt::class], version = 1)
abstract class ReceiptDatabase : RoomDatabase(){

    abstract fun receiptDao(): ReceiptDao

    companion object { // 單例設計
        @Volatile
        private var INSTANCE: ReceiptDatabase? = null

        fun getDatabase(context: Context): ReceiptDatabase{
            val tempInstance = INSTANCE

            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instant = Room.databaseBuilder(
                    context.applicationContext,
                    ReceiptDatabase::class.java,
                    "receipt_dp"
                ).build()
                INSTANCE = instant
                return instant
            }
        }
    }
}