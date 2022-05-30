package com.sollwar.coldboot

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.sollwar.coldboot.databinding.ActivityMainBinding

const val COLD_BOOT_COUNTER = "COLD_BOOT_COUNTER"
const val APP_PREFERENCES = "APP_PREFERENCES"

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        coldBoot()
    }

    private fun coldBoot() {
        sharedPreferences = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        viewModel.increaseColdBootCounter(sharedPreferences.getInt(COLD_BOOT_COUNTER, 0))
        binding.counter.text = viewModel.getColdBootCounter().toString()
        if (viewModel.getColdBootCounter() == 3) {
            Toast.makeText(this, "3 холодный старт", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        sharedPreferences .edit().putInt(
                COLD_BOOT_COUNTER,
                viewModel.getColdBootCounter()
            ).apply()
    }
}