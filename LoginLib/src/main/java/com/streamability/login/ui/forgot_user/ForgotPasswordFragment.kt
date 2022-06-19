package com.streamability.login.ui.forgot_user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.streamability.login.R
import com.streamability.login.databinding.FragmentForgotPasswordBinding
import com.streamability.login.databinding.FragmentLoginBinding
import com.streamability.login.ui.login_user.LoginViewModel

class ForgotPasswordFragment : Fragment() {
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding: FragmentForgotPasswordBinding = _binding!!

    private val viewModel by viewModels<ForgotPasswordViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentForgotPasswordBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
    }

    private fun initListeners() {
        TODO("Not yet implemented")
    }
}