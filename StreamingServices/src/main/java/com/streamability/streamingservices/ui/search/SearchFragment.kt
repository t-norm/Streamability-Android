package com.streamability.streamingservices.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.streamability.datalayer.domain.models.searchMovie.Result
import com.streamability.datalayer.utils.Resource
import com.streamability.streamingservices.R
import com.streamability.streamingservices.databinding.FragmentSearchBinding
import com.streamability.streamingservices.ui.search.adapter.SearchResultsAdapter
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
        toggleDarkMode()
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

    private fun observeSearchState() = with(binding){
        viewModel.searchMovieState.observe(viewLifecycleOwner) { searchMovie ->
            when (searchMovie) {
                is Resource.Success -> {
                    searchProgressBar.visibility = View.GONE
                    if (searchMovie.data?.results!!.isNotEmpty()) {
                        populateSearchResultsRecyclerView(searchMovie.data?.results!!)
                    } else {
                        Snackbar.make(
                            searchProgressBar,
                            "Your search returned no results. Try again.",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
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

    private fun populateSearchResultsRecyclerView(movieResults: List<Result>) = with(binding) {
        val recyclerview = options.optionsMenuRecyclerview
        val adapter = SearchResultsAdapter(::navigateToWatchProviders).apply {applyData(movieResults)}

        recyclerview.searchResultsRecyclerview.layoutManager = LinearLayoutManager(activity)
        recyclerview.searchResultsRecyclerview.adapter = adapter
    }

    private fun toggleDarkMode() = with(binding){
        fun getMenuItem(menuItem: Int): MenuItem {
            return options.navView.menu.findItem(menuItem)
        }

        if(AppCompatDelegate.getDefaultNightMode()== AppCompatDelegate.MODE_NIGHT_YES)
        {
            // darkmode
            getMenuItem(R.id.darkmode_item).setIcon(R.drawable.ic_baseline_light_mode_24)
            getMenuItem(R.id.darkmode_item).title = "Light Mode"

            searchFragmentViewContainer.background = activity?.let {
                ContextCompat.getDrawable(it, R.drawable.dark_mode_img)
            }

            options.searchBar.background = activity?.let {
                ContextCompat.getDrawable(it, R.drawable.rounded_gray)
            }

            options.appBarLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_blue))
            options.topAppBar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.transparent))
        } else {
            // lightmode
            getMenuItem(R.id.darkmode_item).setIcon(R.drawable.ic_baseline_dark_mode_24)
            getMenuItem(R.id.darkmode_item).title = "Dark Mode"

            searchFragmentViewContainer.background = activity?.let {
                ContextCompat.getDrawable(it, R.drawable.random_img_download)
            }

            options.searchBar.background = activity?.let {
                ContextCompat.getDrawable(it, R.drawable.rounded_white)
            }

            options.appBarLayout.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
            options.topAppBar.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.blue))
        }
    }

    private fun navigateToWatchProviders(id: Int) {
        findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailsFragment(id))
    }
}