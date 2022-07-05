package com.streamability.streamingservices.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.streamability.datalayer.domain.models.searchMovie.Result
import com.streamability.datalayer.utils.Resource
import com.streamability.streamingservices.R
import com.streamability.streamingservices.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
                    // code here
                    true
                }
                R.id.logout_item -> {
                    // code here
                    lifecycleScope.launch {
                        delay(500)
                        findNavController().popBackStack()
                    }
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
}

private fun observeSearchState() = with(binding){
    viewModel.searchMovieState.observe(viewLifecycleOwner) { searchMovie ->
        when (searchMovie) {
            is Resource.Success -> {
                searchProgressBar.visibility = android.view.View.GONE
                if (searchMovie.data?.results!!.isNotEmpty()) {
                    populateSearchResultsRecyclerView(searchMovie.data?.results!!)
                } else {
                    com.google.android.material.snackbar.Snackbar.make(
                        searchProgressBar,
                        "Your search returned no results. Try again.",
                        com.google.android.material.snackbar.Snackbar.LENGTH_LONG
                    ).show()
                }
            }
            is Resource.Loading -> {
                searchProgressBar.visibility = android.view.View.VISIBLE
            }
            is Resource.Error -> {
                com.google.android.material.snackbar.Snackbar.make(
                    searchProgressBar,
                    "An error occurred, please try again.",
                    com.google.android.material.snackbar.Snackbar.LENGTH_LONG
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

    if(androidx.appcompat.app.AppCompatDelegate.getDefaultNightMode()== androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES)
    {
        // darkmode
        getMenuItem(com.streamability.streamingservices.R.id.darkmode_item).setIcon(com.streamability.streamingservices.R.drawable.ic_baseline_light_mode_24)
        getMenuItem(com.streamability.streamingservices.R.id.darkmode_item).title = "Light Mode"

        searchFragmentViewContainer.background = activity?.let {
            androidx.core.content.ContextCompat.getDrawable(it, com.streamability.streamingservices.R.drawable.dark_mode_img)
        }

        options.searchBar.background = activity?.let {
            androidx.core.content.ContextCompat.getDrawable(it, com.streamability.streamingservices.R.drawable.rounded_gray)
        }

        options.appBarLayout.setBackgroundColor(androidx.core.content.ContextCompat.getColor(requireContext(), com.streamability.streamingservices.R.color.dark_blue))
        options.topAppBar.setBackgroundColor(androidx.core.content.ContextCompat.getColor(requireContext(), com.streamability.streamingservices.R.color.transparent))
    } else {
        // lightmode
        getMenuItem(com.streamability.streamingservices.R.id.darkmode_item).setIcon(com.streamability.streamingservices.R.drawable.ic_baseline_dark_mode_24)
        getMenuItem(com.streamability.streamingservices.R.id.darkmode_item).title = "Dark Mode"

        searchFragmentViewContainer.background = activity?.let {
            androidx.core.content.ContextCompat.getDrawable(it, com.streamability.streamingservices.R.drawable.random_img_download)
        }

        options.searchBar.background = activity?.let {
            androidx.core.content.ContextCompat.getDrawable(it, com.streamability.streamingservices.R.drawable.rounded_white)
        }

        options.appBarLayout.setBackgroundColor(androidx.core.content.ContextCompat.getColor(requireContext(), com.streamability.streamingservices.R.color.blue))
        options.topAppBar.setBackgroundColor(androidx.core.content.ContextCompat.getColor(requireContext(), com.streamability.streamingservices.R.color.blue))
    }
}

private fun navigateToWatchProviders(id: Int) {
    findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailsFragment(id))
}