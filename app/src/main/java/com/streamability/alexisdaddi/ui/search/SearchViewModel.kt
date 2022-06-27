package com.streamability.alexisdaddi.ui.search

import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.provider.Settings.System.getString
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streamability.alexisdaddi.R
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