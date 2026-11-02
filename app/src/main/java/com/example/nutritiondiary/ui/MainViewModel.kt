package com.example.nutritiondiary.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.nutritiondiary.data.database.AppDatabase
import com.example.nutritiondiary.data.entity.FoodProduct
import com.example.nutritiondiary.data.entity.MealEntry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getInstance(application)
    private val productDao = database.foodProductDao()
    private val mealEntryDao = database.mealEntryDao()

    fun searchProducts(query: String): Flow<List<FoodProduct>> {
        return productDao.searchByName(query)
    }

    fun getTodayEntries(startOfDay: Long, endOfDay: Long): Flow<List<MealEntry>> {
        return mealEntryDao.getTodayEntries(startOfDay, endOfDay)
    }

    fun getTodayCalories(startOfDay: Long, endOfDay: Long): Flow<Double?> {
        return mealEntryDao.getTodayCalories(startOfDay, endOfDay)
    }

    fun addMealEntry(entry: MealEntry) {
        viewModelScope.launch {
            mealEntryDao.insertMealEntry(entry)
        }
    }

    fun addProduct(product: FoodProduct) {
        viewModelScope.launch {
            productDao.insertProduct(product)
        }
    }
}