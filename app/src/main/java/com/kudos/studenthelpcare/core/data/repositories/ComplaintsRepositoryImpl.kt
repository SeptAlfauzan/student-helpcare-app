package com.kudos.studenthelpcare.core.data.repositories

import com.kudos.studenthelpcare.core.data.source.datastore.DataStorePreference
import com.kudos.studenthelpcare.core.data.source.remote.response.ComplaintsResponse
import com.kudos.studenthelpcare.core.data.source.remote.response.PostComplaintResponse
import com.kudos.studenthelpcare.core.data.source.remote.response.SchoolsResponse
import com.kudos.studenthelpcare.core.data.source.remote.services.StudentHelpcareAPIServices
import com.kudos.studenthelpcare.core.domain.entities.ComplaintBody
import com.kudos.studenthelpcare.core.domain.repositories.ComplaintsRepository
import com.kudos.studenthelpcare.core.domain.repositories.SchoolRespository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class ComplaintsRepositoryImpl @Inject constructor(
    private val dataStorePreference: DataStorePreference,
    private val studentHelpcareAPIServices: StudentHelpcareAPIServices,
) : ComplaintsRepository {
    override suspend fun getMyComplaints(): Flow<ComplaintsResponse> {
        try {
            val token = dataStorePreference.getAuthToken().first()
            val result = studentHelpcareAPIServices.complaints("Bearer $token")
            return flowOf(result)
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun postComplaints(complaint: String): Flow<PostComplaintResponse> {
        try {
            val token = dataStorePreference.getAuthToken().first()
            val result = studentHelpcareAPIServices.postComplaint(
                "Bearer $token", body = ComplaintBody(
                    description = complaint
                )
            )
            return flowOf(result)
        } catch (e: Exception) {
            throw e
        }
    }
}