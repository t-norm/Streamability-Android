package com.streamability.alexisdaddi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.streamability.alexisdaddi.R
import com.streamability.alexisdaddi.databinding.FragmentTestBinding

class TestFragment : Fragment() {
    private var _binding: FragmentTestBinding? = null
    private val binding: FragmentTestBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentTestBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        initView()
        initListener()
    }

    private fun initListener() {
        binding.textView.setOnClickListener {
            initView()
        }
    }

    private fun initView() {
        findNavController().navigate(TestFragmentDirections.actionTestFragmentToNavGraph())
    }
}