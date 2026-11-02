package com.example.nutritiondiary.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.nutritiondiary.data.dao.FoodProductDao
import com.example.nutritiondiary.data.dao.MealEntryDao
import com.example.nutritiondiary.data.dao.UserDao
import com.example.nutritiondiary.data.entity.FoodProduct
import com.example.nutritiondiary.data.entity.MealEntry
import com.example.nutritiondiary.data.entity.User

@Database(
    entities = [User::class, FoodProduct::class, MealEntry::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun foodProductDao(): FoodProductDao
    abstract fun mealEntryDao(): MealEntryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "nutrition_diary.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}