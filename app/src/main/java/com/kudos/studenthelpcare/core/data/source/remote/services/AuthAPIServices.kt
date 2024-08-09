package com.kudos.studenthelpcare.core.data.source.remote.services

import com.kudos.studenthelpcare.core.data.source.remote.response.LoginResponse
import com.kudos.studenthelpcare.core.data.source.remote.response.SignupResponse
import com.kudos.studenthelpcare.core.domain.entities.LoginBody
import com.kudos.studenthelpcare.core.domain.entities.SignupBody
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPIServices {
    @POST("api/users/login")
    suspend fun login(@Body body: LoginBody) : LoginResponse

    @POST("api/users")
    suspend fun register(@Body body: SignupBody) : SignupResponse
}