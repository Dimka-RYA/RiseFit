package com.example.risefit

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.risefit.R

class TrainingDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.training_detail_screen)
        
        // Получаем данные из Intent
        val training = intent.getSerializableExtra("TRAINING") as? Training
        
        if (training != null) {
            // Находим View элементы в layout
            val titleTextView = findViewById<TextView>(R.id.training_title)
            val dateTextView = findViewById<TextView>(R.id.training_date)
            val descriptionTextView = findViewById<TextView>(R.id.training_description)
            
            // Устанавливаем данные в View
            titleTextView.text = training.title
            dateTextView.text = training.date
            descriptionTextView.text = training.description
        }
        
        // Настраиваем кнопку "Назад"
        findViewById<View>(R.id.back_button).setOnClickListener {
            // Закрываем текущую активность и возвращаемся на предыдущий экран
            finish()
        }
    }
} 