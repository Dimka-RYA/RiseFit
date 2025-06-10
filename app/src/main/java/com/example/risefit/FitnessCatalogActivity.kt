package com.example.risefit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.risefit.R

class FitnessCatalogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fitness_catalog_screen)
        
        // Находим кнопку "назад" и устанавливаем обработчик
        findViewById<View>(R.id.back_button).setOnClickListener {
            // Закрываем текущую активность и возвращаемся на предыдущий экран
            finish()
        }

        // Находим кнопку профиля и устанавливаем обработчик для перехода на главный экран
        findViewById<View>(R.id.profile_icon).setOnClickListener {
            // Создаем Intent для перехода на главный экран
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }
} 