package com.example.risefit

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ShablonPlanDetailActivity : AppCompatActivity() {
    private var templateIndex: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shablon_plan_detail_screen)

        val doneButton = findViewById<CardView>(R.id.btnDone)
        val templateTitle = findViewById<EditText>(R.id.et_template_title)
        val templateContent = findViewById<EditText>(R.id.et_template_content)

        templateIndex = intent.getIntExtra("TEMPLATE_INDEX", -1)

        if (templateIndex != -1) {
            val template = TemplateRepository.templates[templateIndex]
            templateTitle.setText(template.title)
            templateContent.setText(template.description)
        }

        doneButton.setOnClickListener {
            val title = templateTitle.text.toString()
            val description = templateContent.text.toString()

            if (title.isNotBlank()) {
                val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                val currentDate = sdf.format(Date())

                if (templateIndex != -1) {
                    val templateToUpdate = TemplateRepository.templates[templateIndex]
                    templateToUpdate.title = title
                    templateToUpdate.description = description
                    templateToUpdate.date = currentDate
                } else {
                    TemplateRepository.templates.add(TemplateRepository.Template(title, description, currentDate))
                }
            }
            finish() // Закрываем текущую активность и возвращаемся на предыдущий экран
        }
    }
} 