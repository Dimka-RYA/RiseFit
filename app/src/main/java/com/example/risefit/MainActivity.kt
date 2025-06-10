package com.example.risefit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.risefit.auth.AuthManager
import com.example.risefit.databinding.ActivityMainBinding
import com.example.risefit.ExerciseActivity
import com.example.risefit.MainScreenActivity

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var authManager: AuthManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Инициализация AuthManager
        authManager = AuthManager.getInstance(this)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        // Проверка авторизации и направление на соответствующий экран
        if (authManager.isLoggedIn()) {
            val role = authManager.getCurrentUserRole()
            if (role == "coach") {
                // Для тренера запускаем главное меню
                val intent = Intent(this, MainScreenActivity::class.java)
                startActivity(intent)
                finish()
            } else if (role == "athlete") {
                // Для спортсмена запускаем ExerciseActivity и завершаем MainActivity
                val intent = Intent(this, ExerciseActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        
        // Для демонстрации работы экранов - раскомментируйте следующую строку,
        // чтобы сразу переходить на экран упражнений при запуске приложения
        // startExerciseActivity()
    }
    
    private fun startExerciseActivity() {
        val intent = Intent(this, ExerciseActivity::class.java)
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}