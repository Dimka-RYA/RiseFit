package com.example.risefit

import android.app.Activity
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

    private lateinit var clientManager: ClientManager
    private lateinit var clientsRecycler: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var adapter: ClientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen)

        clientManager = ClientManager.getInstance(this)
        val clientsList = clientManager.getClients().toMutableList()

        if (clientsList.isEmpty()) {
            clientManager.addClient(Client("Тестовый клиент", "Описание тестового клиента", "01.01.2025"))
            clientsList.addAll(clientManager.getClients())
        }

        clientsRecycler = findViewById(R.id.clients_recycler)
        searchEditText = findViewById(R.id.search_edit_text)

        findViewById<View>(R.id.btn_add_client_container).setOnClickListener {
            val intent = Intent(this, NutritionPlanActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_ADD_CLIENT)
        }

        findViewById<ImageView>(R.id.profile_icon).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        adapter = ClientAdapter(clientsList,
            onDelete = { position ->
                val clientToRemove = adapter.getClientAt(position)
                clientManager.removeClient(clientToRemove)
                adapter.removeItem(position)
            },
            onEdit = { position ->
                val intent = Intent(this, NutritionPlanActivity::class.java)
                intent.putExtra("client_index", position)
                startActivityForResult(intent, REQUEST_CODE_EDIT_CLIENT)
            }
        )
        clientsRecycler.layoutManager = LinearLayoutManager(this)
        clientsRecycler.adapter = adapter

        searchEditText.doOnTextChanged { text, _, _, _ ->
            adapter.filter(text?.toString() ?: "")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && data != null) {
            val name = data.getStringExtra("plan_name") ?: ""
            val desc = data.getStringExtra("plan_desc") ?: ""
            val date = data.getStringExtra("plan_date") ?: ""
            val newClient = Client(name, desc, date)

            when (requestCode) {
                REQUEST_CODE_ADD_CLIENT -> {
                    clientManager.addClient(newClient)
                    adapter.addItem(newClient)
                }
                REQUEST_CODE_EDIT_CLIENT -> {
                    val index = data.getIntExtra("client_index", -1)
                    if (index != -1) {
                        clientManager.updateClient(index, newClient)
                        adapter.updateItem(index, newClient)
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        adapter.filter(searchEditText.text.toString())
    }

    companion object {
        private const val REQUEST_CODE_ADD_CLIENT = 1
        private const val REQUEST_CODE_EDIT_CLIENT = 2
    }
} 