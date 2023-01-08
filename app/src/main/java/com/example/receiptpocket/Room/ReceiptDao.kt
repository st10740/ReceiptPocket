package com.example.receiptpocket.Room

import androidx.room.*

@Dao
interface ReceiptDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: Receipt)

    @Query("SELECT * FROM Receipt WHERE sid= :id")
    fun getAll(id: String): List<Receipt>

    @Query("SELECT * FROM Receipt WHERE sid= :id AND year= :year AND month= :month")
    fun findByMonth(id: String, year: Int, month: Int): List<Receipt>

    @Query("SELECT * FROM Receipt WHERE sid= :id AND year= :year AND month= :month AND day= :day")
    fun findByDate(id: String, year: Int, month: Int, day: Int): List<Receipt>

    @Query("DELETE FROM Receipt")
    fun deleteAll()

    @Update
    fun update(item: Receipt)

    @Delete
    fun delete(item: Receipt)


}