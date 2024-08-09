package com.kudos.studenthelpcare.core.domain.repositories

import com.kudos.studenthelpcare.core.data.source.remote.response.ComplaintsResponse
import com.kudos.studenthelpcare.core.data.source.remote.response.PostComplaintResponse
import kotlinx.coroutines.flow.Flow

interface ComplaintsRepository {
    suspend fun getMyComplaints() : Flow<ComplaintsResponse>
    suspend fun postComplaints(complaint: String) : Flow<PostComplaintResponse>
}