package com.example.risefit

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ClientManager(context: Context) {

    companion object {
        private const val PREF_NAME = "RiseFitClients"
        private const val KEY_CLIENTS = "clients"

        private var instance: ClientManager? = null
        fun getInstance(context: Context): ClientManager {
            if (instance == null) {
                instance = ClientManager(context.applicationContext)
            }
            return instance!!
        }
    }

    private val sharedPrefs: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    private val gson = Gson()

    // Получение списка клиентов
    fun getClients(): List<Client> {
        val json = sharedPrefs.getString(KEY_CLIENTS, null) ?: return emptyList()
        val type = object : TypeToken<List<Client>>() {}.type
        return gson.fromJson(json, type)
    }

    // Сохранение списка клиентов
    fun saveClients(clients: List<Client>) {
        val json = gson.toJson(clients)
        sharedPrefs.edit().putString(KEY_CLIENTS, json).apply()
    }

    // Добавление клиента
    fun addClient(client: Client) {
        val list = getClients().toMutableList()
        list.add(client)
        saveClients(list)
    }

    // Удаление клиента
    fun removeClient(client: Client) {
        val list = getClients().toMutableList()
        list.remove(client)
        saveClients(list)
    }
} 