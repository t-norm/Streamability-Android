package com.streamability.streamingservices.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import com.streamability.streamingservices.R
import com.streamability.streamingservices.databinding.DetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private var _binding: DetailsFragmentBinding? = null
    private val binding: DetailsFragmentBinding get() = _binding!!

    private val viewModel by viewModels<DetailsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.isDarkMode()
    }

    // override lifecycle functions
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = DetailsFragmentBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initListeners()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // custom functions
    private fun initViews() {
        getDarkMode()
    }

    private fun initListeners() {

    }

    private fun getDarkMode() = with(binding) {

        viewModel.darkMode.observe(viewLifecycleOwner) { isDarkMode ->
            if (isDarkMode) {
                detailsFragmentLayout.background = activity?.let {
                    androidx.core.content.ContextCompat.getDrawable(it, R.drawable.dark_mode_img)
                }
            } else {
                detailsFragmentLayout.background = activity?.let {
                    androidx.core.content.ContextCompat.getDrawable(
                        it,
                        R.drawable.random_img_download
                    )
                }
            }
        }
    }
}