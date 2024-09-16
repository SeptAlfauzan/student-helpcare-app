package com.kudos.studenthelpcare.core.presentation.forgotpassword

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudos.studenthelpcare.core.domain.repositories.AuthRepository
import com.kudos.studenthelpcare.core.utils.ResultState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private var _state: MutableStateFlow<ResultState<String>> = MutableStateFlow(ResultState.Empty)
    val state: StateFlow<ResultState<String>> = _state

    fun sendResetPasswordToEmail(email: String) {
        viewModelScope.launch {
            _state.value = ResultState.Loading
            authRepository.forgotPassword(email, onSuccess = {
                _state.value = ResultState.Success("Success to reset password")
            }, onFail = {
                _state.value = ResultState.Fail(it)
            })
        }
    }
    fun resetState(){
        viewModelScope.launch {
            _state.value = ResultState.Empty
        }
    }
}