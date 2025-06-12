package com.example.risefit;

import java.util.ArrayList;
import java.util.List;

public class TemplateRepository {
    public static class Template {
        public String title;
        public String description;
        public String date;

        public Template(String title, String description, String date) {
            this.title = title;
            this.description = description;
            this.date = date;
        }
    }

    public static List<Template> templates = new ArrayList<>();

    static {
        // Добавляем тестовые данные
        templates.add(new Template("Тренировка на все тело", "Базовые упражнения для проработки всех групп мышц.", "15.07.2024"));
        templates.add(new Template("Кардио день", "Интенсивная кардио-тренировка для сжигания калорий.", "14.07.2024"));
    }
} 