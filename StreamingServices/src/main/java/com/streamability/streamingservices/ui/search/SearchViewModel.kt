package com.streamability.streamingservices.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streamability.datalayer.domain.useCases.AuthUseCase
import com.streamability.datalayer.domain.useCases.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases,
    private val loginUseCases: AuthUseCase
    ) : ViewModel() {

    fun searchMovie(apiKey: String, query: String) = viewModelScope.launch(Dispatchers.Main) {
        movieUseCases.searchMovieUseCase(apiKey, query)
    }

    fun deleteDataStore() = viewModelScope.launch{
        loginUseCases.deleteDataStoreUseCase()
    }
}