package com.streamability.alexisdaddi.ui.results

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.streamability.alexisdaddi.R
import com.streamability.alexisdaddi.databinding.ResultsFragmentBinding
import com.streamability.alexisdaddi.ui.results.ResultsViewModel

class ResultsFragment : Fragment() {
    private var _binding: ResultsFragmentBinding? = null
    private val binding: ResultsFragmentBinding get() = _binding!!

    private val viewModel by viewModels<ResultsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = ResultsFragmentBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}