package com.example.nutritiondiary.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.nutritiondiary.data.entity.MealEntry
import kotlinx.coroutines.flow.Flow

@Dao
interface MealEntryDao {
    @Insert
    suspend fun insertMealEntry(entry: MealEntry)

    @Query("SELECT * FROM meal_entry WHERE timestamp >= :startOfDay AND timestamp <= :endOfDay ORDER BY timestamp DESC")
    fun getTodayEntries(startOfDay: Long, endOfDay: Long): Flow<List<MealEntry>>

    @Query("SELECT SUM(calories) FROM meal_entry WHERE timestamp >= :startOfDay AND timestamp <= :endOfDay")
    fun getTodayCalories(startOfDay: Long, endOfDay: Long): Flow<Double?>
}