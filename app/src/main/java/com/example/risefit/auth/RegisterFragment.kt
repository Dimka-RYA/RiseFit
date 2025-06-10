package com.example.risefit.auth

import android.content.Context
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ClickableSpan
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.risefit.R
import com.example.risefit.databinding.FragmentRegisterBinding
import android.text.TextPaint
import android.widget.Button
import android.widget.ImageView
import android.widget.ImageButton
import android.widget.Toast

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var authManager: AuthManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация AuthManager
        authManager = AuthManager.getInstance(requireContext())

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.registerButton.setOnClickListener {
            registerUser()
        }

        binding.goToLoginButton.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        // Обработчики для иконок социальных сетей
        binding.telegramIcon.setOnClickListener {
            Toast.makeText(context, "Регистрация через Telegram", Toast.LENGTH_SHORT).show()
        }
        
        binding.vkIcon.setOnClickListener {
            Toast.makeText(context, "Регистрация через ВКонтакте", Toast.LENGTH_SHORT).show()
        }
        
        binding.whatsappIcon.setOnClickListener {
            Toast.makeText(context, "Регистрация через WhatsApp", Toast.LENGTH_SHORT).show()
        }
    }

    private fun registerUser() {
        val username = binding.loginEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString().trim()
        
        // Проверка на пустые поля
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
            return
        }
        
        // Получаем выбранную роль из SharedPreferences
        val sharedPrefs = requireActivity().getSharedPreferences("RiseFitAuth", Context.MODE_PRIVATE)
        val selectedRole = sharedPrefs.getString("selected_role", "athlete") ?: "athlete"
        
        // Регистрация пользователя
        val success = authManager.register(username, password, selectedRole)
        
        if (success) {
            Toast.makeText(context, "Регистрация успешна!", Toast.LENGTH_SHORT).show()
            
            // Перенаправляем на соответствующий экран
            if (selectedRole == "coach") {
                findNavController().navigate(R.id.action_registerFragment_to_coachFragment)
            } else {
                findNavController().navigate(R.id.action_registerFragment_to_athleteFragment)
            }
        } else {
            Toast.makeText(context, "Пользователь с таким именем уже существует", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 