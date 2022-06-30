package com.streamability.streamingservices.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streamability.datalayer.domain.useCases.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val useCases: MovieUseCases) : ViewModel() {
    fun getMovieDetails(movieId: Int, apiKey: String) = viewModelScope.launch(Dispatchers.Main) {
        useCases.movieDetailsUseCase(movieId, apiKey)
    }
}