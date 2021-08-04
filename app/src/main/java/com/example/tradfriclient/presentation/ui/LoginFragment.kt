package com.example.tradfriclient.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.tradfriclient.presentation.MainActivity
import com.example.tradfriclient.R
import com.example.tradfriclient.data.util.Resource
import com.example.tradfriclient.data.util.SessionManager
import com.example.tradfriclient.databinding.FragmentLoginBinding
import com.example.tradfriclient.presentation.util.hideKeyboard
import com.example.tradfriclient.presentation.viewmodel.LoginViewModel

class LoginFragment : Fragment() {
    private lateinit var viewmodel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding
    private lateinit var sessionManager: SessionManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        viewmodel = (activity as MainActivity).loginViewModel
        sessionManager = SessionManager(requireContext())
        setListeners()
    }

    private fun setListeners() {
        binding.loginBtn.setOnClickListener {
            this.hideKeyboard()
            if (binding.securityKeyEt.text.toString()
                    .isNotEmpty() && binding.gatewayIpEt.text.toString().isNotEmpty()
            ) {
                viewmodel.login(
                    binding.securityKeyEt.text.toString(),
                    binding.gatewayIpEt.text.toString()
                )
                viewmodel.test.observe(viewLifecycleOwner, { response ->
                    when (response) {
                        is Resource.Success -> {
                            sessionManager.saveAuthToken(response.data!!.token)
                            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRoomsFragment())
                            binding.progressCircular.isVisible = false
                        }
                        is Resource.Loading -> {
                            binding.progressCircular.isVisible = true
                        }
                        is Resource.Error -> {
                            binding.progressCircular.isVisible = false
                        }
                    }
                })
            }
        }
    }
}