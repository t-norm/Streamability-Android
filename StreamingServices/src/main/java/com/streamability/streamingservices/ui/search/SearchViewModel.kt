package com.streamability.streamingservices.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streamability.datalayer.domain.useCases.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCases: UseCases) : ViewModel() {
    fun searchMovie(apiKey: String, query: String) = viewModelScope.launch(Dispatchers.Main) {
        useCases.searchMovieUseCase(apiKey, query)
    }
}