package com.kudos.studenthelpcare.core.di.modules

import com.kudos.studenthelpcare.core.data.repositories.SchoolRepositoriesImpl
import com.kudos.studenthelpcare.core.data.source.remote.services.StudentHelpcareAPIServices
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
object SchoolModule {
    @Provides
    @Singleton
    fun provideSchoolRepository(studentHelpcareAPIServices: StudentHelpcareAPIServices): SchoolRespository =
        SchoolRepositoriesImpl(studentHelpcareAPIServices)
}