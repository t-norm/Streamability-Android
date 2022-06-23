package com.streamability.login.ui.sign_in_up

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.streamability.login.R
import com.streamability.login.databinding.AccountFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

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
        if (args.login == "signIn"){
            accountFragTitle.text = getString(R.string.sign_in)
            signInUpBtn.text = getString(R.string.sign_in)
            forgotPassword.visibility = View.VISIBLE
        }else{
            accountFragTitle.text = getString(R.string.sign_up)
            signInUpBtn.text = getString(R.string.sign_up)
            forgotPassword.visibility = View.GONE
        }
    }

    private fun initListeners() {
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}