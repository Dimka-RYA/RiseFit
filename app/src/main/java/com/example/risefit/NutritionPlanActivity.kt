package com.example.risefit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.risefit.R
import com.example.risefit.NutritionPlanDetailActivity
import com.example.risefit.TrainingPlanDetailActivity
import com.example.risefit.ClientManager
import com.example.risefit.Client

class NutritionPlanActivity : AppCompatActivity() {

    private var clientIndex: Int = -1
    private lateinit var clientManager: ClientManager
    private lateinit var nameEditText: EditText
    private lateinit var descEditText: EditText
    private lateinit var dateEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nutrition_plan_screen)
        
        // Инициализация менеджера клиентов и получение индекса клиента
        clientManager = ClientManager.getInstance(this)
        clientIndex = intent.getIntExtra("client_index", -1)

        // Инициализация полей
        nameEditText = findViewById(R.id.nutrition_plan_name)
        descEditText = findViewById(R.id.nutrition_plan_description)
        dateEditText = findViewById(R.id.nutrition_plan_date)
        // Заполняем поля, если редактируем существующего клиента
        if (clientIndex != -1) {
            val client = clientManager.getClients()[clientIndex]
            nameEditText.setText(client.name)
            descEditText.setText(client.description)
            dateEditText.setText(client.date)
        }
        
        // Настройка кнопки "Назад"
        findViewById<View>(R.id.back_button).setOnClickListener {
            // Сохраняем изменения клиента и возвращаем данные
            val name = nameEditText.text.toString()
            val desc = descEditText.text.toString()
            val date = dateEditText.text.toString()
            // Обновляем список клиентов
            if (clientIndex != -1) {
                val clients = clientManager.getClients().toMutableList()
                clients[clientIndex] = Client(name, desc, date)
                clientManager.saveClients(clients)
            }
            // Возвращаем результат
            val resultIntent = Intent()
            resultIntent.putExtra("client_index", clientIndex)
            resultIntent.putExtra("plan_name", name)
            resultIntent.putExtra("plan_desc", desc)
            resultIntent.putExtra("plan_date", date)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
        
        // Здесь только пустой шаблон, данные не заполняем
        
        // Настройка кнопок действий
        setupActionButtons()
    }
    
    private fun setupActionButtons() {
        // Настройка кнопки добавления плана питания
        findViewById<View>(R.id.add_nutrition_plan_button).setOnClickListener {
            // Открываем экран деталей плана питания
            val intent = Intent(this, NutritionPlanDetailActivity::class.java)
            startActivity(intent)
        }
        
        // Настройка кнопки добавления плана тренировки
        findViewById<View>(R.id.add_training_plan_button).setOnClickListener {
            // Открываем экран деталей плана тренировки
            val intent = Intent(this, TrainingPlanDetailActivity::class.java)
            startActivity(intent)
        }
    }
} 