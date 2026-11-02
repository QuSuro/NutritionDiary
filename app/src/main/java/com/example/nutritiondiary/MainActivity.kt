package com.example.nutritiondiary

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.nutritiondiary.databinding.ActivityMainBinding
import com.example.nutritiondiary.ui.MainViewModel
import kotlinx.coroutines.launch
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setupUI()
        observeData()
    }

    private fun setupUI() {
        binding.editTextSearch.setOnEditorActionListener { _, _, _ ->
            val query = binding.editTextSearch.text.toString()
            if (query.isNotEmpty()) {
                searchProducts(query)
            }
            true
        }
    }

    private fun observeData() {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        val startOfDay = calendar.timeInMillis

        calendar.set(Calendar.HOUR_OF_DAY, 23)
        calendar.set(Calendar.MINUTE, 59)
        calendar.set(Calendar.SECOND, 59)
        val endOfDay = calendar.timeInMillis

        lifecycleScope.launch {
            viewModel.getTodayCalories(startOfDay, endOfDay).collect { calories ->
                binding.textViewCalories.text = "Калории за сегодня: ${calories?.toInt() ?: 0}"
            }
        }
    }

    private fun searchProducts(query: String) {
        lifecycleScope.launch {
            viewModel.searchProducts(query).collect { products ->
                products.forEach { product ->
                    println("Найден продукт: ${product.name} - ${product.caloriesPer100g} ккал")
                }
            }
        }
    }
}