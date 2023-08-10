package com.example.datastorekullanimi.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.datastorekullanimi.R
import com.example.datastorekullanimi.common.AppPrefs
import com.example.datastorekullanimi.common.viewBinding
import com.example.datastorekullanimi.databinding.FragmentSignupBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SignupFragment : Fragment(R.layout.fragment_signup) {

    private val binding by viewBinding(FragmentSignupBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            button.setOnClickListener {
                val userName = etName.text.toString()
                val userSurname = etSurname.text.toString()
                val userAge = etAge.text.toString().toInt()
                var userMaritalStats = false

                if (switch1.isChecked) {
                    userMaritalStats = true
                }

                saveUserData(userName, userSurname, userAge, userMaritalStats)
                Toast.makeText(requireContext(), "User registered successfully", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.signupToProfile)
            }
        }
    }

    private fun saveUserData(name: String, surname: String, age: Int, maritalStats: Boolean) {

        CoroutineScope(Dispatchers.Main).launch {
            AppPrefs(requireContext()).saveUser(name, surname, age, maritalStats)
        }

    }
}