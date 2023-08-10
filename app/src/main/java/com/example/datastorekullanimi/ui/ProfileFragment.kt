package com.example.datastorekullanimi.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import com.example.datastorekullanimi.R
import com.example.datastorekullanimi.common.AppPrefs
import com.example.datastorekullanimi.common.viewBinding
import com.example.datastorekullanimi.databinding.FragmentProfileBinding


class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val binding by viewBinding(FragmentProfileBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    fun observeData() {
        AppPrefs(requireContext()).userNameFlow.asLiveData()
            .observe(viewLifecycleOwner) { user_name ->
                user_name?.let {
                    binding.tvName.text = user_name
                }
            }

        AppPrefs(requireContext()).userSurnameFlow.asLiveData()
            .observe(viewLifecycleOwner) { user_surname ->
                user_surname?.let {
                    binding.tvSurname.text = user_surname
                }
            }

        AppPrefs(requireContext()).userAgeFlow.asLiveData()
            .observe(viewLifecycleOwner) { user_age ->
                user_age?.let {
                    binding.tvAge.text = user_age.toString()
                }
            }

        AppPrefs(requireContext()).userMaritalStatsFlow.asLiveData()
            .observe(viewLifecycleOwner) { user_marital_status ->
                user_marital_status?.let {
                    if(user_marital_status){
                        binding.tvMaritalStats.text = "Married"
                    }else{
                        binding.tvMaritalStats.text = "Not Married"
                    }
                }
            }
    }
}