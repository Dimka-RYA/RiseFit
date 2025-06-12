package com.example.risefit;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TrainingTemplatesActivity extends AppCompatActivity {

    private LinearLayout templatesContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_templates_screen);

        templatesContainer = findViewById(R.id.templates_list_container);
        CardView addTemplateButton = findViewById(R.id.btnAddTemplate);

        addTemplateButton.setOnClickListener(v -> {
            Intent intent = new Intent(TrainingTemplatesActivity.this, ShablonPlanDetailActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateTemplatesList();
    }

    private void updateTemplatesList() {
        templatesContainer.removeAllViews(); // Очищаем список перед обновлением

        for (int i = 0; i < TemplateRepository.templates.size(); i++) {
            final int index = i;
            TemplateRepository.Template template = TemplateRepository.templates.get(index);

            LayoutInflater inflater = LayoutInflater.from(this);
            View templateView = inflater.inflate(R.layout.template_list_item, templatesContainer, false);

            TextView title = templateView.findViewById(R.id.tvTemplateTitle);
            TextView date = templateView.findViewById(R.id.tvTemplateDate);
            TextView description = templateView.findViewById(R.id.tvTemplateDescription);
            CardView goToTemplateButton = templateView.findViewById(R.id.btn_go_to_template);

            title.setText(template.title);
            date.setText(template.date);
            description.setText(template.description);

            goToTemplateButton.setOnClickListener(v -> {
                Intent intent = new Intent(TrainingTemplatesActivity.this, ShablonPlanDetailActivity.class);
                intent.putExtra("TEMPLATE_INDEX", index);
                startActivity(intent);
            });

            templatesContainer.addView(templateView, 0); // Добавляем новый шаблон в начало списка
        }
    }
} 