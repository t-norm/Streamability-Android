package com.streamability.login.ui.login_user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

import com.streamability.login.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding get() = _binding!!

    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentLoginBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() = with(binding) {
        var loginParam = "signIn"

        fun destination() = findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToAccountFragment(loginParam))

        signInBtn.setOnClickListener {
            destination()
        }

        signUpBtn.setOnClickListener {
            loginParam = "signUp"
            destination()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}