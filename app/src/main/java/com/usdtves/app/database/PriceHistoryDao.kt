package com.usdtves.app.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PriceHistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(history: PriceHistoryEntity)

    @Query("SELECT * FROM price_history ORDER BY date DESC")
    suspend fun getAllHistory(): List<PriceHistoryEntity>

    @Query("SELECT * FROM price_history ORDER BY date DESC LIMIT :limit")
    suspend fun getRecentHistory(limit: Int): List<PriceHistoryEntity>

    @Query("SELECT * FROM price_history WHERE date = :date LIMIT 1")
    suspend fun getHistoryByDate(date: String): PriceHistoryEntity?

    @Query("SELECT COUNT(*) FROM price_history")
    suspend fun getHistoryCount(): Int

    @Query("DELETE FROM price_history WHERE date < :beforeDate")
    suspend fun deleteOlderThan(beforeDate: String)
}
