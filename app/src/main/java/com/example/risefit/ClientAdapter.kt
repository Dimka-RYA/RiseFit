package com.example.risefit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClientAdapter(
    private val originalList: MutableList<Client>,
    private val onDelete: (Client) -> Unit,
    private val onEdit: (Client) -> Unit
) : RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {

    private var displayList: MutableList<Client> = originalList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_client, parent, false)
        return ClientViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client = displayList[position]
        holder.name.text = client.name
        holder.description.text = client.description
        holder.date.text = client.date
        holder.deleteButton.setOnClickListener {
            onDelete(client)
            originalList.remove(client)
            filter(currentFilter)
        }
        holder.editButton.setOnClickListener {
            onEdit(client)
        }
    }

    override fun getItemCount(): Int = displayList.size

    inner class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.client_name)
        val description: TextView = itemView.findViewById(R.id.client_description)
        val date: TextView = itemView.findViewById(R.id.client_date)
        val deleteButton: ImageButton = itemView.findViewById(R.id.delete_button)
        val editButton: ImageButton = itemView.findViewById(R.id.edit_button)
    }

    private var currentFilter: String = ""

    fun filter(query: String) {
        currentFilter = query
        displayList.clear()
        if (query.isEmpty()) {
            displayList.addAll(originalList)
        } else {
            val lowerQuery = query.lowercase()
            displayList.addAll(originalList.filter {
                it.name.lowercase().contains(lowerQuery)
            })
        }
        notifyDataSetChanged()
    }
} 