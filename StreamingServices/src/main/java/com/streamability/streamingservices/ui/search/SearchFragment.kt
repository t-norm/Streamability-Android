package com.streamability.streamingservices.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.streamability.datalayer.utils.Resource
import com.streamability.streamingservices.R
import com.streamability.streamingservices.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding: FragmentSearchBinding get() = _binding!!

    private val viewModel by viewModels<SearchViewModel>()

    // override lifecycle functions
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentSearchBinding.inflate(inflater, container, false).also {
        _binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
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
        fun getMenuItem(menuItem: Int): MenuItem = with(binding) {
            return options.navView.menu.findItem(menuItem)
        }

        if(AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES)
        {
            getMenuItem(R.id.darkmode_item).setIcon(R.drawable.ic_baseline_light_mode_24)
            getMenuItem(R.id.darkmode_item).title = "Light Mode"
        } else {
            getMenuItem(R.id.darkmode_item).setIcon(R.drawable.ic_baseline_dark_mode_24)
            getMenuItem(R.id.darkmode_item).title = "Dark Mode"
        }
    }

    private fun initListeners() = with(binding) {
        val hamburgerMenu = options.topAppBar
        val settingsMenu = options.navView
        val searchBar = options.searchBar

        val apiKey = resources.getString(R.string.moviedb_api_key)

        hamburgerMenu.setNavigationOnClickListener{
            options.drawerLayout.openDrawer(GravityCompat.START)
        }

        settingsMenu.setNavigationItemSelectedListener { menuItem ->
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
                    /* TODO: code for changing region */
                    true
                }
                R.id.logout_item -> {
                    /* TODO: code for logging out */
                    true
                }
                else -> {
                    false
                }
            }
        }


        searchBar.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(queryString: String): Boolean {
                viewModel.searchMovie(apiKey, queryString)

                observeSearchState()
                return false
            }

            override fun onQueryTextChange(queryString: String): Boolean {
                return false
            }
        })
    }

    private fun navigateToWatchProviders(){
        findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailsFragment())
    }

    private fun observeSearchState() = with(binding){
        viewModel.searchMovieState.observe(viewLifecycleOwner) { searchMovie ->
            when (searchMovie) {
                is Resource.Success -> {
                    searchProgressBar.visibility = View.INVISIBLE
                }
                is Resource.Loading -> {
                    searchProgressBar.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    Snackbar.make(
                        searchProgressBar,
                        "An error occurred, please try again.",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}