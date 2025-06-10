package com.example.risefit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.risefit.R

class TrainingArchiveActivity : AppCompatActivity() {

    // Список тренировок
    private val trainingList = listOf(
        Training(
            1, 
            "Тренировка 1", 
            "01.06.2023", 
            "Описание тренировки 1:\n\n" +
            "• Разминка 5 минут\n" +
            "• Приседания: 3 подхода по 12 повторений\n" +
            "• Жим лежа: 4 подхода по 10 повторений\n" +
            "• Тяга верхнего блока: 3 подхода по 12 повторений\n" +
            "• Отжимания: 3 подхода по 15 повторений\n" +
            "• Планка: 3 подхода по 1 минуте\n" +
            "• Растяжка: 5 минут"
        ),
        Training(
            2, 
            "Тренировка 2", 
            "05.06.2023", 
            "Описание тренировки 2:\n\n" +
            "• Кардио разминка: 7 минут\n" +
            "• Подтягивания: 3 подхода по 8 повторений\n" +
            "• Становая тяга: 4 подхода по 8 повторений\n" +
            "• Жим гантелей сидя: 3 подхода по 12 повторений\n" +
            "• Скручивания на пресс: 3 подхода по 20 повторений\n" +
            "• Выпады с гантелями: 3 подхода по 12 на каждую ногу\n" +
            "• Заминка: 5 минут"
        ),
        Training(
            3, 
            "Тренировка 3", 
            "10.06.2023", 
            "Описание тренировки 3:\n\n" +
            "• Разминка на велотренажере: 5 минут\n" +
            "• Приседания с гантелями: 3 подхода по 15 повторений\n" +
            "• Отжимания на брусьях: 3 подхода по 10 повторений\n" +
            "• Тяга гантели в наклоне: 3 подхода по 12 повторений\n" +
            "• Жим гантелей лежа: 3 подхода по 12 повторений\n" +
            "• Планка боковая: 3 подхода по 45 секунд на каждую сторону\n" +
            "• Растяжка: 10 минут"
        ),
        Training(
            4, 
            "Тренировка 4", 
            "15.06.2023", 
            "Описание тренировки 4:\n\n" +
            "• Интенсивная кардио-разминка: 10 минут\n" +
            "• Круговая тренировка (3 круга):\n" +
            "  - Берпи: 15 повторений\n" +
            "  - Прыжки на скакалке: 1 минута\n" +
            "  - Приседания с прыжком: 20 повторений\n" +
            "  - Отжимания: 15 повторений\n" +
            "  - Скручивания: 25 повторений\n" +
            "• Заминка: 5 минут растяжки"
        ),
        Training(
            5, 
            "Тренировка 5", 
            "20.06.2023", 
            "Описание тренировки 5:\n\n" +
            "• Легкая разминка: 5 минут\n" +
            "• Йога-комплекс:\n" +
            "  - Поза горы: 1 минута\n" +
            "  - Поза собаки мордой вниз: 1 минута\n" +
            "  - Поза воина: 30 секунд на каждую сторону\n" +
            "  - Поза дерева: 30 секунд на каждую ногу\n" +
            "  - Поза голубя: 1 минута на каждую сторону\n" +
            "  - Поза ребенка: 1 минута\n" +
            "• Медитация: 10 минут"
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.training_archive_screen)
        
        // Настраиваем кнопку "Назад"
        findViewById<View>(R.id.back_button).setOnClickListener {
            // Закрываем текущую активность и возвращаемся на предыдущий экран
            finish()
        }
        
        // Настраиваем кнопки перехода к деталям тренировок
        setupTrainingButtons()
    }
    
    private fun setupTrainingButtons() {
        // Кнопка для первой тренировки
        findViewById<View>(R.id.btn_training_detail_1).setOnClickListener {
            navigateToTrainingDetail(trainingList[0])
        }
        
        // Кнопка для второй тренировки
        findViewById<View>(R.id.btn_training_detail_2).setOnClickListener {
            navigateToTrainingDetail(trainingList[1])
        }
        
        // Кнопка для третьей тренировки
        findViewById<View>(R.id.btn_training_detail_3).setOnClickListener {
            navigateToTrainingDetail(trainingList[2])
        }
        
        // Кнопка для четвертой тренировки
        findViewById<View>(R.id.btn_training_detail_4).setOnClickListener {
            navigateToTrainingDetail(trainingList[3])
        }
        
        // Кнопка для пятой тренировки
        findViewById<View>(R.id.btn_training_detail_5).setOnClickListener {
            navigateToTrainingDetail(trainingList[4])
        }
    }
    
    private fun navigateToTrainingDetail(training: Training) {
        val intent = Intent(this, TrainingDetailActivity::class.java).apply {
            putExtra("TRAINING", training)
        }
        startActivity(intent)
    }
} 