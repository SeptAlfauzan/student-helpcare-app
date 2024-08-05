package com.kudos.studenthelpcare.core.domain.repositories

import com.kudos.studenthelpcare.core.data.source.remote.response.SchoolsResponse
import kotlinx.coroutines.flow.Flow

interface SchoolRespository {
    suspend fun getSchools(): Flow<SchoolsResponse>
}