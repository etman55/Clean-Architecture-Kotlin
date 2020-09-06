package com.atef.sample.feature.usermanagement.verifyemail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.atef.core.ext.doAfterTextChanged
import com.atef.sample.base.BaseFragment
import com.atef.sample.databinding.FragmentVerifyEmailBinding

class VerifyEmailFragment : BaseFragment() {

    private var _binding: FragmentVerifyEmailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVerifyEmailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        verify()
        binding.backBtn.setOnClickListener {
            userManagementNavigator.openSplash(this)
        }
    }

    private fun verify() {
        binding.oneCodeInput.doAfterTextChanged {
            if (it.toString().length == 1)
                binding.twoCodeInput.requestFocus()
        }
        binding.twoCodeInput.doAfterTextChanged {
            if (it.toString().length == 1)
                binding.threeCodeInput.requestFocus()
            else
                binding.oneCodeInput.requestFocus()
        }
        binding.threeCodeInput.doAfterTextChanged {
            if (it.toString().length == 1)
                binding.fourCodeInput.requestFocus()
            else
                binding.twoCodeInput.requestFocus()
        }
        binding.fourCodeInput.doAfterTextChanged {
            if (it.toString().length == 1)
                binding.fiveCodeInput.requestFocus()
            else
                binding.threeCodeInput.requestFocus()
        }
        binding.fiveCodeInput.doAfterTextChanged {
            if (it.toString().length == 1)
                binding.fourCodeInput.requestFocus()
        }

        binding.confirmBtn.setOnClickListener {
            val oneStr = binding.oneCodeInput.editableText.toString().trim()
            val twoStr = binding.twoCodeInput.editableText.toString().trim()
            val threeStr = binding.threeCodeInput.editableText.toString().trim()
            val fourStr = binding.fourCodeInput.editableText.toString().trim()
            val fiveStr = binding.fiveCodeInput.editableText.toString().trim()
            val codeStr = "$oneStr$twoStr$threeStr$fourStr$fiveStr"
            userManagementNavigator.openMainActivity(requireActivity())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
