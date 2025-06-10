package com.example.risefit.auth

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AuthManager(context: Context) {
    
    companion object {
        private const val PREF_NAME = "RiseFitAuth"
        private const val KEY_USERS = "users"
        private const val KEY_CURRENT_USER = "current_user"
        
        private var instance: AuthManager? = null
        
        fun getInstance(context: Context): AuthManager {
            if (instance == null) {
                instance = AuthManager(context.applicationContext)
            }
            return instance!!
        }
    }
    
    private val sharedPrefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()
    
    // Сохранение списка пользователей
    private fun saveUsers(users: List<User>) {
        val json = gson.toJson(users)
        sharedPrefs.edit().putString(KEY_USERS, json).apply()
    }
    
    // Получение списка пользователей
    private fun getUsers(): List<User> {
        val json = sharedPrefs.getString(KEY_USERS, null) ?: return emptyList()
        val type = object : TypeToken<List<User>>() {}.type
        return gson.fromJson(json, type)
    }
    
    // Регистрация нового пользователя
    fun register(username: String, password: String, role: String): Boolean {
        val users = getUsers().toMutableList()
        
        // Проверяем, существует ли пользователь с таким именем
        if (users.any { it.username == username }) {
            return false // Пользователь уже существует
        }
        
        // Добавляем нового пользователя
        users.add(User(username, password, role))
        saveUsers(users)
        
        // Автоматически входим после регистрации
        setCurrentUser(username)
        
        return true
    }
    
    // Вход пользователя
    fun login(username: String, password: String): Boolean {
        val users = getUsers()
        
        // Проверяем, существует ли пользователь с таким именем и паролем
        val user = users.find { it.username == username && it.password == password }
        
        if (user != null) {
            // Сохраняем текущего пользователя
            setCurrentUser(username)
            return true
        }
        
        return false
    }
    
    // Получение роли текущего пользователя
    fun getCurrentUserRole(): String? {
        val currentUsername = getCurrentUser() ?: return null
        val users = getUsers()
        
        return users.find { it.username == currentUsername }?.role
    }
    
    // Сохранение текущего пользователя
    private fun setCurrentUser(username: String) {
        sharedPrefs.edit().putString(KEY_CURRENT_USER, username).apply()
    }
    
    // Получение имени текущего пользователя
    fun getCurrentUser(): String? {
        return sharedPrefs.getString(KEY_CURRENT_USER, null)
    }
    
    // Выход пользователя
    fun logout() {
        sharedPrefs.edit().remove(KEY_CURRENT_USER).apply()
    }
    
    // Проверка, вошел ли пользователь
    fun isLoggedIn(): Boolean {
        return getCurrentUser() != null
    }
} 