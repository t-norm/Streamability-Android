package com.streamability.streamingservices.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streamability.datalayer.domain.models.movieDetails.MovieDetailsModel
import com.streamability.datalayer.domain.models.searchMovie.Result
import com.streamability.datalayer.domain.useCases.MovieUseCases
import com.streamability.datalayer.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val useCases: MovieUseCases) : ViewModel() {
    private val _searchMovieState = MutableLiveData<Resource<List<Result>>>(Resource.Loading())
    val searchMovieState: LiveData<Resource<List<Result>>> get() = _searchMovieState

    fun searchMovie(apiKey: String, query: String) = viewModelScope.launch(Dispatchers.Main) {
       _searchMovieState.value = useCases.searchMovieUseCase(apiKey, query)
    }

}