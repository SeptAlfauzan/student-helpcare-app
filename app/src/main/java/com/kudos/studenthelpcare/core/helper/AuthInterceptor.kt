package com.kudos.studenthelpcare.core.helper

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.kudos.studenthelpcare.core.data.source.datastore.DataStorePreference
import com.kudos.studenthelpcare.core.data.source.remote.response.LoginResponse
import com.kudos.studenthelpcare.core.data.source.remote.services.AuthAPIServices
import com.kudos.studenthelpcare.core.data.source.remote.services.StudentHelpcareAPIServices
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject


class AuthInterceptor @Inject constructor(
    private val authAPIServices: AuthAPIServices,
    private val dataStorePreference: DataStorePreference
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        // Acquire the lock to ensure only one thread executes the refresh logic
//        refreshTokenLock.withLock {
        val response = chain.proceed(originalRequest)
        if(response.code == 200) Log.d("TAG", "intercept firebase token interceptor: okay")
        if (response.code == 401) {
            Log.d( this.javaClass.simpleName, "intercept: get new token")
               val newToken = runBlocking {
                    callRefreshTokenAPI()
                }

            Log.d("TAG", "intercept: get new token $newToken")
            response.close()
            val newRequest = originalRequest.newBuilder()
                .header("Authorization", "Bearer ${newToken}")
                .build()

           return chain.proceed(newRequest)
        }
        return response
    }

    private suspend fun callRefreshTokenAPI(): String {
        try {
            val previousLoginData = dataStorePreference.getPreviousLoginData().first() ?: return ""

            Log.d("TAG", "callRefreshTokenAPI: $previousLoginData")
            val result = authAPIServices.login(previousLoginData!!)

            dataStorePreference.setAuthToken(result.data?.token ?: "")

            return result.data?.token ?: ""
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("TAG", "intercept: get new token ERROR ${e.message} ")
            return ""
        }
    }
}