package com.kudos.studenthelpcare.core.data.source.remote.services

import com.kudos.studenthelpcare.core.data.source.remote.response.ComplaintsResponse
import com.kudos.studenthelpcare.core.data.source.remote.response.LoginResponse
import com.kudos.studenthelpcare.core.data.source.remote.response.PostComplaintResponse
import com.kudos.studenthelpcare.core.data.source.remote.response.UserProfileResponse
import com.kudos.studenthelpcare.core.domain.entities.ComplaintBody
import com.kudos.studenthelpcare.core.domain.entities.LoginBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface StudentHelpcareAPIServices {
    @POST("api/complaints")
    suspend fun postComplaint(@Header("Authorization") token: String,  @Body body: ComplaintBody) : PostComplaintResponse
    @GET("api/complaints")
    suspend fun complaints(@Header("Authorization") token: String) : ComplaintsResponse

    @GET("api/users/profile")
    suspend fun getProfile(@Header("Authorization") token: String) : UserProfileResponse
}