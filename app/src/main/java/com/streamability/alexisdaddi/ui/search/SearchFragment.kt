package com.streamability.alexisdaddi.ui.search


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.fragment.app.viewModels
import com.streamability.alexisdaddi.R
import com.streamability.alexisdaddi.databinding.SearchFragmentBinding
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)

        val topAppBar = options.topAppBar
        val menu = options.navView

        if(AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES)
        {
            getMenuItem(R.id.darkmode_item).setIcon(R.drawable.ic_baseline_light_mode_24)
            getMenuItem(R.id.darkmode_item).title = "Light Mode"
        } else {
            getMenuItem(R.id.darkmode_item).setIcon(R.drawable.ic_baseline_dark_mode_24)
            getMenuItem(R.id.darkmode_item).title = "Dark Mode"
        }


        topAppBar.setNavigationOnClickListener{
            options.drawerLayout.openDrawer(GravityCompat.START)
        }

        menu.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.darkmode_item -> {
                    if(AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES)
                    {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                    true
                }
                R.id.region_item -> {
                    // code here
                    true
                }
                R.id.logout_item -> {
                    // code here
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun getMenuItem(menuItem: Int): MenuItem = with(binding) {
        return options.navView.menu.findItem(menuItem)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}