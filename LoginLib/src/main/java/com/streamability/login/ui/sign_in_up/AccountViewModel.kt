package com.streamability.login.ui.sign_in_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.streamability.datalayer.domain.useCases.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val authUseCase: AuthUseCase
) : ViewModel() {
    lateinit var username: String
    lateinit var password: String
    lateinit var loginUser: String
    lateinit var loginPass: String

    private val _isUsernameFilled: MutableLiveData<Boolean> = MutableLiveData(false)

    private val _isPasswordFilled: MutableLiveData<Boolean> = MutableLiveData(false)

    private val _signInSuccess: MutableLiveData<Boolean> = MutableLiveData()
    val signInSuccess: LiveData<Boolean> = _signInSuccess

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

    fun setFilledFormToFalse(){
        _isUsernameFilled.value = false
        _isPasswordFilled.value = false
    }

    fun isLoginUsernameFilled(flag: Boolean){
        _isUsernameFilled.value = flag
    }

    fun isLoginPasswordFilled(flag: Boolean){
        _isPasswordFilled.value = flag
    }

    fun setDataStore(user: String, pw: String) = viewModelScope.launch{
        username = user
        password = pw
        _signInSuccess.value = authUseCase.setDataStoreUseCase(username, password)
    }

    fun getDataStore() = viewModelScope.launch(Dispatchers.Main){
       val user =  authUseCase.getDataStoreUseCase().singleOrNull()
       if (user?.username != null && user.password != ""){
           loginUser = user.username
           loginPass = user.password
       }
    }

}