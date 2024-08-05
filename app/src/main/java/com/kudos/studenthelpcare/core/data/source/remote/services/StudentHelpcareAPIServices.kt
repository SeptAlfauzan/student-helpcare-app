package com.kudos.studenthelpcare.core.data.source.remote.services

import com.kudos.studenthelpcare.core.data.source.remote.response.SchoolsResponse
import retrofit2.http.GET

interface StudentHelpcareAPIServices {
    @GET("api/schools")
    suspend fun getSchools():SchoolsResponse
}