package com.kudos.studenthelpcare.core.presentation.signup

import com.kudos.studenthelpcare.core.domain.entities.SignupBody
import com.kudos.studenthelpcare.core.domain.repositories.AuthRepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    fun signup(
        signupBody: SignupBody,
        onSuccess: () -> Unit,
        onFail: (String) -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                authRepository.signup(signupBody)

                withContext(Dispatchers.Main) {
                    onSuccess()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onFail(e.message.toString())
                }
            }
        }
    }
}