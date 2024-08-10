package com.kudos.studenthelpcare.core.domain.repositories

import com.kudos.studenthelpcare.core.data.source.remote.response.UserProfileResponse
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {
    suspend fun getProfile() : Flow<UserProfileResponse>
}