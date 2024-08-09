package com.kudos.studenthelpcare.core.data.repositories

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.kudos.studenthelpcare.core.data.source.datastore.DataStorePreference
import com.kudos.studenthelpcare.core.data.source.remote.services.AuthAPIServices
import com.kudos.studenthelpcare.core.data.source.remote.services.StudentHelpcareAPIServices
import com.kudos.studenthelpcare.core.domain.entities.LoginBody
import com.kudos.studenthelpcare.core.domain.entities.SignupBody
import com.kudos.studenthelpcare.core.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authAPIServices: AuthAPIServices,
    private val dataStorePreference: DataStorePreference,
) : AuthRepository {
    override suspend fun signin(body: LoginBody): Flow<Boolean> {
        try {
            val result = authAPIServices.login(body)
            dataStorePreference.setAuthToken(result.data?.token ?: "")
            dataStorePreference.setPreviousLoginData(body)

            return flowOf(true)
        }catch (e: Exception){
            throw e
        }
    }

    override suspend fun signup(body: SignupBody): Flow<Boolean> {
        try {
            authAPIServices.register(body)
            return flowOf(true)
        }catch (e: Exception){
            throw e
        }
    }

    override suspend fun isLogged(): Flow<Boolean> {
        val token = dataStorePreference.getAuthToken().first()
        return flowOf(token.isNotEmpty())
    }

    override suspend fun logout(): Flow<Boolean> {
        try {
            dataStorePreference.setAuthToken("")
            return flowOf(true)
        }catch (e: Exception){
            throw e
        }
    }

    override suspend fun forgotPassword(email: String, onSuccess: () -> Unit, onFail: (Exception) -> Unit) {
            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener {
                onSuccess()
            }.addOnFailureListener { onFail(it) }
    }
}