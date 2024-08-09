package com.kudos.studenthelpcare.core.domain.repositories

import com.kudos.studenthelpcare.core.domain.entities.LoginBody
import com.kudos.studenthelpcare.core.domain.entities.SignupBody
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signin(body: LoginBody) : Flow<Boolean>
    suspend fun signup(body: SignupBody) : Flow<Boolean>
    suspend fun isLogged() : Flow<Boolean>
    suspend fun logout() : Flow<Boolean>

    suspend fun forgotPassword(email: String, onSuccess: () -> Unit, onFail: (Exception) -> Unit)
}