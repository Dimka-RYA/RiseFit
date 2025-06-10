package com.example.risefit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.risefit.TrainingArchiveActivity;
import com.example.risefit.TrainingTemplatesActivity;

public class NutritionPlanDetailActivity extends AppCompatActivity {

    private CardView btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nutrition_plan_detail_screen);

        // Инициализация кнопки "Готово"
        btnDone = findViewById(R.id.btnDone);

        // Обработчик нажатия "Готово"
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Завершение редактирования плана питания
                finish();
            }
        });
    }
} 