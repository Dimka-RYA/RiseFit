package com.example.risefit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.risefit.R
import com.example.risefit.TrainingArchiveActivity
import com.example.risefit.TrainingTemplatesActivity

class TrainingPlanDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.treni_plan_detail_screen)

        // Инициализация кнопок
        val btnGoToArchive = findViewById<CardView>(R.id.btnGoToArchive)
        val btnUseTemplate = findViewById<CardView>(R.id.btnUseTemplate)
        val btnDone = findViewById<CardView>(R.id.btnDone)

        // Обработчики нажатий
        btnGoToArchive.setOnClickListener {
            startActivity(Intent(this, TrainingArchiveActivity::class.java))
        }
        btnUseTemplate.setOnClickListener {
            startActivity(Intent(this, TrainingTemplatesActivity::class.java))
        }
        btnDone.setOnClickListener {
            finish()
        }
    }
} 