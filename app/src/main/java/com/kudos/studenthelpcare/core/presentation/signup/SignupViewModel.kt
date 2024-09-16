package com.kudos.studenthelpcare.core.presentation.signup

import com.kudos.studenthelpcare.core.domain.entities.SignupBody
import com.kudos.studenthelpcare.core.domain.repositories.AuthRepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private var _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private var _isError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError

    fun resetIsError() {
        viewModelScope.launch(Dispatchers.IO) {
            _isError.value = false
        }
    }

    fun signup(
        signupBody: SignupBody,
        onSuccess: () -> Unit,
        onFail: (String) -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true

            try {
                authRepository.signup(signupBody)

                withContext(Dispatchers.Main) {
                    onSuccess()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onFail(e.message.toString())
                }
                _isError.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }
}