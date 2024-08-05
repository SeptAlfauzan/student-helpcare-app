package com.kudos.studenthelpcare.core.data.repositories

import com.kudos.studenthelpcare.core.data.source.remote.response.SchoolsResponse
import com.kudos.studenthelpcare.core.data.source.remote.services.StudentHelpcareAPIServices
import com.kudos.studenthelpcare.core.domain.repositories.SchoolRespository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class SchoolRepositoriesImpl @Inject constructor(
private val studentHelpcareAPIServices: StudentHelpcareAPIServices
) : SchoolRespository {
    override suspend fun getSchools(): Flow<SchoolsResponse> {
       try {
           return flowOf(studentHelpcareAPIServices.getSchools())
       }catch (e: Exception){
           throw e
       }
    }
}