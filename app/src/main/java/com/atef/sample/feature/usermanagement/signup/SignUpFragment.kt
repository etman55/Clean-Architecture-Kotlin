package com.atef.sample.feature.usermanagement.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.atef.sample.base.BaseFragment
import com.atef.sample.databinding.FragmentSignUpBinding

class SignUpFragment : BaseFragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerBtn.setOnClickListener {
            userManagementNavigator.openVerifyEmail(it.findNavController(), "email")
        }
        binding.LoginBtn.setOnClickListener {
            userManagementNavigator.openLogin(it.findNavController())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
