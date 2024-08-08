package com.kudos.studenthelpcare.core.domain.repositories

import com.kudos.studenthelpcare.core.data.source.remote.response.LoginResponse
import com.kudos.studenthelpcare.core.domain.entities.LoginBody
import com.kudos.studenthelpcare.core.domain.entities.RegisterBody
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun signin(body: LoginBody) : Flow<Boolean>
    suspend fun signup(body: RegisterBody) : Flow<Boolean>
    suspend fun isLogged() : Flow<Boolean>
    suspend fun logout() : Flow<Boolean>
}