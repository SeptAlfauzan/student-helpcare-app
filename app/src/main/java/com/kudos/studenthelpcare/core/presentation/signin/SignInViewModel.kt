package com.kudos.studenthelpcare.core.presentation.signin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudos.studenthelpcare.core.domain.entities.LoginBody
import com.kudos.studenthelpcare.core.domain.repositories.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel  @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    fun signin(email: String, password: String){
        viewModelScope.launch {
            try {
                val loginBody = LoginBody(email, password)
                authRepository.signin(loginBody)
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }
}