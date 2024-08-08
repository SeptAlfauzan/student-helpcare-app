package com.kudos.studenthelpcare.core.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudos.studenthelpcare.core.domain.entities.LoginBody
import com.kudos.studenthelpcare.core.domain.repositories.AuthRepository
import com.kudos.studenthelpcare.core.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private var _signinResult: MutableStateFlow<ResultState<Boolean>> =
        MutableStateFlow(ResultState.Empty)
    val signinResult: StateFlow<ResultState<Boolean>> = _signinResult

    private var _isLogged: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
    val isLogged: StateFlow<Boolean> = _isLogged


    init {
        viewModelScope.launch {
            authRepository.isLogged().collect {
                _isLogged.value = it
            }
        }
    }

    fun signin(email: String, password: String) {
        viewModelScope.launch {

            _signinResult.value = ResultState.Loading
            try {
                val loginBody = LoginBody(email, password)
                authRepository.signin(loginBody).catch {
                    _signinResult.value = ResultState.Fail(it)
                }.collect {
                    _signinResult.value = ResultState.Success(it)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _signinResult.value = ResultState.Fail(e)
            }
        }
    }

    fun resetSigninResult() {
        _signinResult.value = ResultState.Empty
    }

    fun logout(){
        try {

        viewModelScope.launch {
            authRepository.logout()
            authRepository.isLogged().collect {
                _isLogged.value = it
            }
        }
        }catch (e: Exception){
         e.printStackTrace()
            throw e
        }
    }
}