package com.streamability.alexisdaddi.ui.root

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streamability.datalayer.domain.models.sharedPref.Login
import com.streamability.datalayer.domain.useCases.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    private val loginUseCases: AuthUseCase
    ): ViewModel() {

    private val _user =  MutableSharedFlow<Login>()
    val user = _user.asSharedFlow()

    fun getUser() = viewModelScope.launch{
        _user.emit(loginUseCases.getDataStoreUseCase().first())
    }

}