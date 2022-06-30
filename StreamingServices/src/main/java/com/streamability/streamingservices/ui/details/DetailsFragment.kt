package com.streamability.streamingservices.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.streamability.streamingservices.databinding.DetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: DetailsFragmentBinding? = null
    private val binding: DetailsFragmentBinding get() = _binding!!

    private val viewModel by viewModels<DetailsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DetailsFragmentBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}