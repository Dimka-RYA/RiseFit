package com.example.risefit

import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.example.risefit.R

class ShablonPlanDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shablon_plan_detail_screen)

        // Установка названия шаблона по индексу
        val index = intent.getIntExtra("template_index", -1)
        val titleView = findViewById<TextView>(R.id.tvNutritionPlanTitle)
        if (index > 0) {
            titleView.text = "Шаблон $index"
        } else {
            titleView.text = "Шаблон тренировки"
        }
    }
} 