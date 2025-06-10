package com.example.risefit.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.risefit.ExerciseActivity
import com.example.risefit.R
import com.example.risefit.databinding.FragmentLoginBinding
import com.example.risefit.MainScreenActivity

class LoginFragmentImpl : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var authManager: AuthManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Инициализация AuthManager
        authManager = AuthManager.getInstance(requireContext())

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.continueButton.setOnClickListener {
            loginUser()
        }
    }

    private fun loginUser() {
        val username = binding.loginEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString().trim()
        
        // Проверка на пустые поля
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(context, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
            return
        }
        
        // Вход пользователя
        val success = authManager.login(username, password)
        
        if (success) {
            // Получаем роль пользователя
            val role = authManager.getCurrentUserRole()
            
            // Перенаправляем на соответствующий экран
            if (role == "coach") {
                // Для тренера запускаем главное меню
                val intent = Intent(requireContext(), MainScreenActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                // Для спортсмена открываем экран упражнений
                val intent = Intent(requireContext(), ExerciseActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
            
            Toast.makeText(context, "Вход успешен!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Неверное имя пользователя или пароль", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 