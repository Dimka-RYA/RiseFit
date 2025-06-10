package com.example.risefit

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText
import android.widget.ImageView
import com.example.risefit.R
import com.example.risefit.NutritionPlanActivity
import com.example.risefit.ProfileActivity

class MainScreenActivity : AppCompatActivity() {
    companion object {
        private const val REQUEST_EDIT_CLIENT = 1001
    }

    private lateinit var clientManager: ClientManager
    private lateinit var clientsRecycler: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var adapter: ClientAdapter
    private var clientsList = mutableListOf<Client>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        clientManager = ClientManager.getInstance(this)
        // Добавляем одного тестового клиента, если список пуст
        if (clientManager.getClients().isEmpty()) {
            clientManager.addClient(Client("Тестовый клиент", "Описание тестового клиента", "01.01.2025"))
        }
        clientsList = clientManager.getClients().toMutableList()

        clientsRecycler = findViewById(R.id.clients_recycler)
        searchEditText = findViewById(R.id.search_edit_text)

        // Навигация на экран добавления/редактирования клиента
        findViewById<View>(R.id.btn_add_client_container).setOnClickListener {
            // Создаем нового пустого клиента и сохраняем
            val newClient = Client("", "", "")
            clientManager.addClient(newClient)
            clientsList.clear()
            clientsList.addAll(clientManager.getClients())
            adapter.notifyDataSetChanged()
            // Переходим на экран редактирования клиента
            val index = clientsList.lastIndex
            val intent = Intent(this, NutritionPlanActivity::class.java)
            intent.putExtra("client_index", index)
            startActivityForResult(intent, REQUEST_EDIT_CLIENT)
        }

        // Навигация на экран профиля по клику на иконку
        findViewById<ImageView>(R.id.profile_icon).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        adapter = ClientAdapter(clientsList,
            onDelete = { client ->
                clientManager.removeClient(client)
                clientsList.remove(client)
            },
            onEdit = { client ->
                val index = clientsList.indexOf(client)
                if (index != -1) {
                    // Переход на экран редактирования клиента
                    val intent = Intent(this, NutritionPlanActivity::class.java)
                    intent.putExtra("client_index", index)
                    startActivityForResult(intent, REQUEST_EDIT_CLIENT)
                }
            }
        )
        clientsRecycler.layoutManager = LinearLayoutManager(this)
        clientsRecycler.adapter = adapter

        // Поиск по имени клиента
        searchEditText.doOnTextChanged { text, _, _, _ ->
            adapter.filter(text?.toString() ?: "")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_EDIT_CLIENT && resultCode == RESULT_OK && data != null) {
            val index = data.getIntExtra("client_index", -1)
            if (index != -1) {
                val name = data.getStringExtra("plan_name") ?: ""
                val desc = data.getStringExtra("plan_desc") ?: ""
                val date = data.getStringExtra("plan_date") ?: ""
                // Обновляем клиента в менеджере и адаптере
                val updated = Client(name, desc, date)
                val clients = clientManager.getClients().toMutableList()
                clients[index] = updated
                clientManager.saveClients(clients)
                clientsList.clear()
                clientsList.addAll(clients)
                adapter.filter(searchEditText.text.toString())
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Обновляем список клиентов после возможного добавления
        clientsList.clear()
        clientsList.addAll(clientManager.getClients())
        adapter.filter(searchEditText.text.toString())
    }
} 