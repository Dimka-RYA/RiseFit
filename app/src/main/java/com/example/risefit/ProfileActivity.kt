package com.example.risefit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.risefit.R
import android.widget.ImageView

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        // Обработчик кнопки назад
        findViewById<ImageView>(R.id.profile_back).setOnClickListener {
            finish()
        }
    }
} 