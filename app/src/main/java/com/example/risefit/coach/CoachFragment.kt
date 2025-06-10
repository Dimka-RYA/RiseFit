package com.example.risefit.coach

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.risefit.R
import com.example.risefit.RoleSelectionFragment
import com.example.risefit.auth.AuthManager

class CoachFragment : Fragment() {

    private lateinit var authManager: AuthManager
    private lateinit var usernameTextView: TextView
    private lateinit var logoutButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_coach, container, false)
        
        authManager = AuthManager.getInstance(requireContext())
        usernameTextView = view.findViewById(R.id.usernameTextView)
        logoutButton = view.findViewById(R.id.logoutButton)
        
        // Отображаем имя пользователя
        usernameTextView.text = "Привет, ${authManager.getCurrentUser() ?: "Тренер"}"
        
        // Настраиваем кнопку выхода
        logoutButton.setOnClickListener {
            authManager.logout()
            // Возвращаемся на экран выбора роли с помощью навигации
            findNavController().navigate(R.id.action_to_role_selection)
        }
        
        return view
    }
} 