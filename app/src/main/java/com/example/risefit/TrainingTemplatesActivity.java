package com.example.risefit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.risefit.ShablonPlanDetailActivity;

public class TrainingTemplatesActivity extends AppCompatActivity {

    private CardView template1;
    private CardView template2;
    private CardView template3;
    private CardView template4;
    private CardView btnAddTemplate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_templates_screen);

        // Инициализация элементов
        template1 = findViewById(R.id.template1);
        template2 = findViewById(R.id.template2);
        template3 = findViewById(R.id.template3);
        template4 = findViewById(R.id.template4);
        btnAddTemplate = findViewById(R.id.btnAddTemplate);

        // Настройка обработчиков нажатий
        template1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Открытие деталей шаблона 1
                Intent intent = new Intent(TrainingTemplatesActivity.this, ShablonPlanDetailActivity.class);
                intent.putExtra("template_index", 1);
                startActivity(intent);
            }
        });

        template2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Открытие деталей шаблона 2
                Intent intent = new Intent(TrainingTemplatesActivity.this, ShablonPlanDetailActivity.class);
                intent.putExtra("template_index", 2);
                startActivity(intent);
            }
        });

        template3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Открытие деталей шаблона 3
                Intent intent = new Intent(TrainingTemplatesActivity.this, ShablonPlanDetailActivity.class);
                intent.putExtra("template_index", 3);
                startActivity(intent);
            }
        });

        template4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Открытие деталей шаблона 4
                Intent intent = new Intent(TrainingTemplatesActivity.this, ShablonPlanDetailActivity.class);
                intent.putExtra("template_index", 4);
                startActivity(intent);
            }
        });

        btnAddTemplate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Переход к созданию нового шаблона
                Intent intent = new Intent(TrainingTemplatesActivity.this, ShablonPlanDetailActivity.class);
                intent.putExtra("template_index", -1);
                startActivity(intent);
            }
        });
    }
} 