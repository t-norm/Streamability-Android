package com.streamability.alexisdaddi.ui.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.streamability.alexisdaddi.R
import com.streamability.alexisdaddi.databinding.ActivityMainBinding.inflate
import com.streamability.alexisdaddi.databinding.DetailsFragmentBinding
import com.streamability.alexisdaddi.databinding.DetailsFragmentBinding.inflate
import com.streamability.alexisdaddi.databinding.ResultsFragmentBinding.inflate

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