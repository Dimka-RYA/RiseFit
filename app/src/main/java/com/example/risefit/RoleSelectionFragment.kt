package com.example.risefit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.content.Context
import androidx.navigation.fragment.findNavController
import com.example.risefit.databinding.FragmentRoleSelectionBinding

class RoleSelectionFragment : Fragment() {

    private var _binding: FragmentRoleSelectionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRoleSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.coachButton.setOnClickListener {
            // Сохраняем выбранную роль в SharedPreferences
            saveSelectedRole("coach")
            findNavController().navigate(R.id.action_roleSelectionFragment_to_registerFragment)
        }

        binding.athleteButton.setOnClickListener {
            // Сохраняем выбранную роль в SharedPreferences
            saveSelectedRole("athlete")
            findNavController().navigate(R.id.action_roleSelectionFragment_to_registerFragment)
        }
    }

    private fun saveSelectedRole(role: String) {
        val sharedPrefs = requireActivity().getSharedPreferences("RiseFitAuth", Context.MODE_PRIVATE)
        sharedPrefs.edit().putString("selected_role", role).apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 