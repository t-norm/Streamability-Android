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
        toggleDarkMode()
    }

    private fun initListeners() {

    }

    private fun toggleDarkMode() = with(binding) {
        fun getMenuItem(menuItem: Int): MenuItem {
            return detailsAppBar.navView.menu.findItem(menuItem)
        }

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            // darkmode
            getMenuItem(R.id.darkmode_item).setIcon(R.drawable.ic_baseline_light_mode_24)
            getMenuItem(R.id.darkmode_item).title = "Light Mode"

            detailsFragmentViewContainer.background = activity?.let {
                androidx.core.content.ContextCompat.getDrawable(it, R.drawable.dark_mode_img)
            }

            detailsAppBar.appBarLayout.setBackgroundColor(
                androidx.core.content.ContextCompat.getColor(
                    requireContext(),
                    R.color.dark_blue
                )
            )

            detailsAppBar.topAppBar.setBackgroundColor(
                androidx.core.content.ContextCompat.getColor(
                    requireContext(),
                    R.color.transparent
                )
            )
        } else {
            // lightmode
            getMenuItem(R.id.darkmode_item).setIcon(R.drawable.ic_baseline_dark_mode_24)
            getMenuItem(R.id.darkmode_item).title = "Dark Mode"

            detailsFragmentViewContainer.background = activity?.let {
                androidx.core.content.ContextCompat.getDrawable(it, R.drawable.random_img_download)
            }

            detailsAppBar.appBarLayout.setBackgroundColor(
                androidx.core.content.ContextCompat.getColor(
                    requireContext(),
                    R.color.blue
                )
            )

            detailsAppBar.topAppBar.setBackgroundColor(
                androidx.core.content.ContextCompat.getColor(
                    requireContext(),
                    R.color.blue
                )
            )
        }
    }
}