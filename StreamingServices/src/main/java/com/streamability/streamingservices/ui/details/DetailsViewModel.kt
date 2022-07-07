package com.streamability.streamingservices.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streamability.datalayer.domain.useCases.DataStoreUseCases
import com.streamability.datalayer.domain.useCases.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val dataStoreUseCases: DataStoreUseCases,
) : ViewModel() {

    private val _darkMode: MutableLiveData<Boolean> = MutableLiveData(false)
    val darkMode: LiveData<Boolean> get() = _darkMode

    fun isDarkMode() = viewModelScope.launch(Dispatchers.Main){
        _darkMode.value = dataStoreUseCases.getDarkModeUseCase().first()
    }
}