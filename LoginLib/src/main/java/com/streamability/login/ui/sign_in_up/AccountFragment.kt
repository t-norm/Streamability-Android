package com.streamability.login.ui.sign_in_up

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.streamability.login.R
import com.streamability.login.databinding.AccountFragmentBinding
import com.streamability.login.ui.login_user.LoginFragmentDirections
import com.streamability.streamingservices.ui.search.SearchFragment
import com.streamability.streamingservices.ui.search.SearchFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private var _binding: AccountFragmentBinding? = null
    private val binding: AccountFragmentBinding get() = _binding!!

    private val args by navArgs<AccountFragmentArgs>()

    private val viewModel by viewModels<AccountViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = AccountFragmentBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initViews()
    }

    private fun initViews() = with(binding) {
        viewModel.setFilledFormToFalse()
        //Set up text and showing of Forgot Password text view
        if (args.login == "signIn") {
            accountFragTitle.text = getString(R.string.sign_in)
            signInUpBtn.text = getString(R.string.sign_in)
            forgotPassword.visibility = View.VISIBLE
        } else {
            accountFragTitle.text = getString(R.string.sign_up)
            signInUpBtn.text = getString(R.string.sign_up)
            forgotPassword.visibility = View.GONE
        }

    }

    private fun initListeners() = with(binding) {
        if (args.login != "signIn") {

            etUserText.setOnFocusChangeListener { _, focused ->
                if (!focused) {
                    if (viewModel.userErrorResponse.value == "Username works!") {
                        etUser.isErrorEnabled = false
                        etUser.error = ""
                    }
                    etUser.isErrorEnabled = true
                } else {
                    if (viewModel.userErrorResponse.value == "Username works!") {
                        etUser.isErrorEnabled = false
                        etUser.error = ""
                    }
                    etUser.isErrorEnabled = true
                }
            }
            etPasswordText.setOnFocusChangeListener { _, focused ->
                if (!focused) {
                    if (viewModel.passwordErrorResponse.value == "Password works!") {
                        etPassword.isErrorEnabled = false
                        etPassword.error = ""
                    }
                    etUser.isErrorEnabled = true
                } else {
                    if (viewModel.passwordErrorResponse.value == "Password works!") {
                        etPassword.isErrorEnabled = false
                        etPassword.error = ""
                    }
                    etUser.isErrorEnabled = false
                }
            }
            etUserText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(string: Editable?) {
                    viewModel.valdateUsername(string.toString())
                    if (viewModel.userErrorResponse.value == "Username works!") {
                        etUser.isErrorEnabled = false
                        etUser.error = ""
                    } else {
                        etUser.isErrorEnabled = true
                        viewModel.userErrorResponse.observe(viewLifecycleOwner) { error ->
                            etUser.error = error
                        }
                    }
                    viewModel.isBtnDisabled()
                }
            })
            etPasswordText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(string: Editable?) {
                    viewModel.validatePassword(string.toString())
                    if (viewModel.passwordErrorResponse.value == "Password works!") {
                        etPassword.isErrorEnabled = false
                        etPassword.error = ""
                    } else {
                        etUser.isErrorEnabled = true
                        viewModel.passwordErrorResponse.observe(viewLifecycleOwner) { error ->
                            if (error != "") {
                                etPassword.error = error
                            }

                        }
                    }
                    viewModel.isBtnDisabled()
                }
            })
            viewModel.isSignUpBtnDisabled.observe(viewLifecycleOwner) { btnDisabled ->
                when (btnDisabled) {
                    true -> {
                        binding.signInUpBtn.isEnabled = false
                        binding.signInUpBtn.isClickable = false
                    }
                    false -> {
                        binding.signInUpBtn.isEnabled = true
                        binding.signInUpBtn.isClickable = true
                    }
                }
            }
        }else{
            viewModel.getDataStore()

            viewModel.isSignUpBtnDisabled.observe(viewLifecycleOwner) { btnDisabled ->
                when (btnDisabled) {
                    true -> {
                        binding.signInUpBtn.isEnabled = false
                        binding.signInUpBtn.isClickable = false
                    }
                    false -> {
                        binding.signInUpBtn.isEnabled = true
                        binding.signInUpBtn.isClickable = true
                    }
                }
            }

            etUserText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(string: Editable?) {
                    viewModel.username = string.toString()
                    if (viewModel.username != "" && viewModel.username.isNotEmpty()){
                        viewModel.isLoginUsernameFilled(true)
                    }else{
                        viewModel.isLoginUsernameFilled(false)
                    }
                    viewModel.isBtnDisabled()
                }
            })
            etPasswordText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(string: Editable?) {
                    viewModel.password = string.toString()
                    if (viewModel.password != "" && viewModel.password.isNotEmpty()){
                        viewModel.isLoginPasswordFilled(true)
                    }else{
                        viewModel.isLoginPasswordFilled(false)
                    }
                    viewModel.isBtnDisabled()
                }
            })
        }
        signInUpBtn.setOnClickListener {
            if (args.login == "signUp") {
                if (viewModel.userErrorResponse.value == "Username works!" && viewModel.passwordErrorResponse.value == "Password works!") {
                    // Save to DataStore
                    val username = binding.etUserText.text.toString()
                    val password = binding.etPasswordText.text.toString()
                    viewModel.setDataStore(username, password)
                    viewModel.signInSuccess.observe(viewLifecycleOwner){ result ->
                        if (result == true){
                            findNavController().popBackStack()
                            findNavController().popBackStack()
                        }else if (result == false){
                            //Sign In Error
                            Snackbar.make(
                                accountFragmentLayout,
                                "An error occurred, please try again.",
                                Snackbar.LENGTH_LONG
                            ).setAction(R.string.retry) {
                                viewModel.setDataStore(username, password)
                            }.show()
                        }
                    }
                }
            }else{
                if (viewModel.username != viewModel.loginUser && viewModel.password != viewModel.loginPass){
                    Snackbar.make(
                        accountFragmentLayout,
                        "Username and Password do not match records, Try again",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}