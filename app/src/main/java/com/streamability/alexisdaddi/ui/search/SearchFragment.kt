package com.streamability.alexisdaddi.ui.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.streamability.alexisdaddi.R
import com.streamability.alexisdaddi.databinding.SearchFragmentBinding
import com.streamability.alexisdaddi.ui.search.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: SearchFragmentBinding? = null
    private val binding: SearchFragmentBinding get() = _binding!!

    private val viewModel by viewModels<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = SearchFragmentBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}