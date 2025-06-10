package com.example.risefit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.risefit.R
import android.widget.TextView
import androidx.cardview.widget.CardView

class ExerciseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exercise_screen)
        
        // Находим кнопку архива тренировок и устанавливаем обработчик нажатия
        findViewById<View>(R.id.archive_button).setOnClickListener {
            // Создаем Intent для перехода на экран архива тренировок
            val intent = Intent(this, TrainingArchiveActivity::class.java)
            startActivity(intent)
        }
        
        // Находим кнопку профиля и устанавливаем обработчик для перехода в каталог упражнений
        findViewById<View>(R.id.profile_icon).setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        
        val trainingPlanButton = findViewById<CardView>(R.id.training_plan_button)
        val nutritionPlanButton = findViewById<CardView>(R.id.nutrition_plan_button)

        trainingPlanButton.setOnClickListener {
            val intent = Intent(this, PlanActivity::class.java)
            startActivity(intent)
        }

        nutritionPlanButton.setOnClickListener {
            val intent = Intent(this, FitnessCatalogActivity::class.java)
            startActivity(intent)
        }
    }
} 