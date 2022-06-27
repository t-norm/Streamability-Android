package com.streamability.login.ui.sign_in_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streamability.datalayer.domain.useCases.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    lateinit var username: String
    lateinit var password: String

    private val _isUsernameFilled: MutableLiveData<Boolean> = MutableLiveData(false)
    val isUsernameFilled: LiveData<Boolean> get() = _isUsernameFilled

    private val _isPasswordFilled: MutableLiveData<Boolean> = MutableLiveData(false)
    val isPasswordFilled: LiveData<Boolean> get() = _isPasswordFilled

    private val _userErrorResponse: MutableLiveData<String> = MutableLiveData()
    val userErrorResponse: LiveData<String> get() = _userErrorResponse

    private val _passwordErrorResponse: MutableLiveData<String> = MutableLiveData()
    val passwordErrorResponse: LiveData<String> get() = _passwordErrorResponse

    private val _isSignUpBtnDisabled: MutableLiveData<Boolean> = MutableLiveData(true)
    val isSignUpBtnDisabled: LiveData<Boolean> get() = _isSignUpBtnDisabled

    fun valdateUsername(input: String){
        _userErrorResponse.value = authUseCase.authUsernameUseCase(input)
        _isUsernameFilled.value = _userErrorResponse.value == "Username works!"
    }

    fun validatePassword(input:String){
        _passwordErrorResponse.value = authUseCase.authPasswordUseCase(input)
        _isPasswordFilled.value = _passwordErrorResponse.value == "Password works!"
    }

    fun isBtnDisabled(){
        _isSignUpBtnDisabled.value = !(_isUsernameFilled.value!! && _isPasswordFilled.value!!)
    }

    fun setDataStore(user: String, pw: String) = viewModelScope.launch{
        username = user
        password = pw
        authUseCase.setDataStoreUseCase(username, password)
    }

}