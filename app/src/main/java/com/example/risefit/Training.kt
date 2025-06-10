package com.example.risefit

import java.io.Serializable

/**
 * Модель данных тренировки
 *
 * @param id уникальный идентификатор тренировки
 * @param title название тренировки
 * @param date дата тренировки
 * @param description описание тренировки
 */
data class Training(
    val id: Int,
    val title: String,
    val date: String,
    val description: String
) : Serializable 