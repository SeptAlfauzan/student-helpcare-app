package com.kudos.studenthelpcare.core.data.source.remote.services

import com.kudos.studenthelpcare.core.data.source.remote.response.LoginResponse
import com.kudos.studenthelpcare.core.domain.entities.LoginBody
import retrofit2.http.Body
import retrofit2.http.POST

interface StudentHelpcareAPIServices {
    @POST("api/users/login")
    suspend fun login(@Body body: LoginBody) : LoginResponse
}