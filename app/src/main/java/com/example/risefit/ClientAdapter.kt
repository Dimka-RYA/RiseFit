package com.example.risefit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClientAdapter(
    private var clients: MutableList<Client>,
    private val onDelete: (Int) -> Unit,
    private val onEdit: (Int) -> Unit
) : RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {

    private var filteredClients: MutableList<Client> = clients.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_client, parent, false)
        return ClientViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client = filteredClients[position]
        holder.bind(client)
    }

    override fun getItemCount(): Int = filteredClients.size

    inner class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.client_name)
        private val description: TextView = itemView.findViewById(R.id.client_description)
        private val date: TextView = itemView.findViewById(R.id.client_date)
        private val deleteButton: ImageButton = itemView.findViewById(R.id.delete_button)
        private val editButton: ImageButton = itemView.findViewById(R.id.edit_button)

        fun bind(client: Client) {
            name.text = client.name
            description.text = client.description
            date.text = client.date

            deleteButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onDelete(position)
                }
            }
            editButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onEdit(position)
                }
            }
        }
    }

    fun filter(query: String) {
        val lowerCaseQuery = query.lowercase()
        filteredClients = if (query.isEmpty()) {
            clients.toMutableList()
        } else {
            clients.filter { it.name.lowercase().contains(lowerCaseQuery) }.toMutableList()
        }
        notifyDataSetChanged()
    }

    fun getClientAt(position: Int): Client {
        return filteredClients[position]
    }

    fun addItem(client: Client) {
        clients.add(0, client)
        filter("")
    }

    fun removeItem(position: Int) {
        val clientToRemove = filteredClients[position]
        clients.remove(clientToRemove)
        filter("")
    }

    fun updateItem(position: Int, client: Client) {
        val originalIndex = clients.indexOf(filteredClients[position])
        if (originalIndex != -1) {
            clients[originalIndex] = client
        }
        filter("")
    }
} 