package com.kudos.studenthelpcare.core.data.repositories

import com.kudos.studenthelpcare.core.data.source.datastore.DataStorePreference
import com.kudos.studenthelpcare.core.data.source.remote.response.LoginResponse
import com.kudos.studenthelpcare.core.data.source.remote.services.StudentHelpcareAPIServices
import com.kudos.studenthelpcare.core.domain.entities.LoginBody
import com.kudos.studenthelpcare.core.domain.entities.RegisterBody
import com.kudos.studenthelpcare.core.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val studentHelpcareAPIServices: StudentHelpcareAPIServices,
    private val dataStorePreference: DataStorePreference,
) : AuthRepository {
    override suspend fun signin(body: LoginBody): Flow<Boolean> {
        try {
            val result = studentHelpcareAPIServices.login(body)
            dataStorePreference.setAuthToken(result.data?.token ?: "")
            dataStorePreference.setPreviousLoginData(body)

            return flowOf(true)
        }catch (e: Exception){
            throw e
        }
    }

    override suspend fun signup(body: RegisterBody): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun isLogged(): Flow<Boolean> {
        return flowOf(dataStorePreference.getAuthToken().first().isEmpty())
    }
}