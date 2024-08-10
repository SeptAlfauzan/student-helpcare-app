package com.kudos.studenthelpcare.core.data.repositories

import com.kudos.studenthelpcare.core.data.source.datastore.DataStorePreference
import com.kudos.studenthelpcare.core.data.source.remote.response.UserProfileResponse
import com.kudos.studenthelpcare.core.data.source.remote.services.StudentHelpcareAPIServices
import com.kudos.studenthelpcare.core.domain.repositories.ProfileRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val dataStorePreference: DataStorePreference,
    private val studentHelpcareAPIServices: StudentHelpcareAPIServices
) : ProfileRepository {
    override suspend fun getProfile(): Flow<UserProfileResponse> {
        try {
            val token = dataStorePreference.getAuthToken().first()
            val result = studentHelpcareAPIServices.getProfile("Bearer $token")
            return flowOf(result)
        }catch (e: Exception){
            throw e
        }
    }
}