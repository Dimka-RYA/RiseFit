package com.example.risefit.athlete

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.risefit.R
import com.example.risefit.auth.AuthManager

class AthleteFragment : Fragment() {

    private lateinit var authManager: AuthManager
    private lateinit var usernameTextView: TextView
    private lateinit var logoutButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_athlete, container, false)
        
        authManager = AuthManager.getInstance(requireContext())
        usernameTextView = view.findViewById(R.id.usernameTextView)
        logoutButton = view.findViewById(R.id.logoutButton)
        
        // Отображаем имя пользователя
        usernameTextView.text = "Привет, ${authManager.getCurrentUser() ?: "Спортсмен"}"
        
        // Настраиваем кнопку выхода
        logoutButton.setOnClickListener {
            authManager.logout()
            // Возвращаемся на экран выбора роли с помощью навигации
            findNavController().navigate(R.id.action_to_role_selection)
        }
        
        return view
    }
} 