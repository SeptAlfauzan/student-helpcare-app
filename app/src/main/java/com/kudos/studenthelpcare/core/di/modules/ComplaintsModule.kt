package com.kudos.studenthelpcare.core.di.modules

import com.kudos.studenthelpcare.core.data.repositories.ComplaintsRepositoryImpl
import com.kudos.studenthelpcare.core.data.repositories.SchoolRepositoriesImpl
import com.kudos.studenthelpcare.core.data.source.datastore.DataStorePreference
import com.kudos.studenthelpcare.core.data.source.remote.services.SchoolAPIServices
import com.kudos.studenthelpcare.core.data.source.remote.services.StudentHelpcareAPIServices
import com.kudos.studenthelpcare.core.domain.repositories.ComplaintsRepository
import com.kudos.studenthelpcare.core.domain.repositories.SchoolRespository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(
    SingletonComponent::class
)
object ComplaintsModule {
    @Provides
    @Singleton
    fun provideComplaintRepository(
        dataStorePreference: DataStorePreference,
        studentHelpcareAPIServices: StudentHelpcareAPIServices): ComplaintsRepository =
        ComplaintsRepositoryImpl(dataStorePreference, studentHelpcareAPIServices)
}