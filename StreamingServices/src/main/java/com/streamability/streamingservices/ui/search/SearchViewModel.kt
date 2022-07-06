package com.streamability.streamingservices.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streamability.datalayer.domain.models.searchMovie.SearchMovieModel
import com.streamability.datalayer.domain.useCases.AuthUseCase
import com.streamability.datalayer.domain.useCases.MovieUseCases
import com.streamability.datalayer.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases,
    private val loginUseCases: AuthUseCase
) : ViewModel() {
    private val _searchMovieState = MutableLiveData<Resource<SearchMovieModel?>>(Resource.Loading())
    val searchMovieState: LiveData<Resource<SearchMovieModel?>> get() = _searchMovieState

    fun searchMovie(apiKey: String, query: String) = viewModelScope.launch(Dispatchers.Main) {
        _searchMovieState.value = movieUseCases.searchMovieUseCase(apiKey, query)
    }

    fun deleteDataStore() = viewModelScope.launch {
        loginUseCases.deleteDataStoreUseCase()
    }
}