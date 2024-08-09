package com.kudos.studenthelpcare.core.di.modules

import com.kudos.studenthelpcare.core.data.source.datastore.DataStorePreference
import com.kudos.studenthelpcare.core.data.source.remote.services.AuthAPIServices
import com.kudos.studenthelpcare.core.data.source.remote.services.SchoolAPIServices
import com.kudos.studenthelpcare.core.data.source.remote.services.StudentHelpcareAPIServices
import com.kudos.studenthelpcare.core.helper.AuthInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModules {

    @Provides
    @Singleton
    fun provideAuthInterceptor(
        authAPIServices: AuthAPIServices,
        dataStorePreference: DataStorePreference,
    ): AuthInterceptor = AuthInterceptor(authAPIServices, dataStorePreference)

    @Provides
    @Singleton
    fun provideOkHttpClientWithInterceptor(
        authInterceptor: AuthInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .addInterceptor(authInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofitWithInterceptor(okHttpClient: OkHttpClient): Retrofit.Builder =
        Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())

    @Provides
    @Singleton
    fun provideStudentHelpcareAPIServices(retrofitBuilder: Retrofit.Builder): StudentHelpcareAPIServices =
        retrofitBuilder.baseUrl("https://student-helpcare-um.vercel.app/").build().create(
            StudentHelpcareAPIServices::class.java
        )

    @Provides
    @Singleton
    fun provideAuthServices(): AuthAPIServices {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val retrofitBuilder = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
        return retrofitBuilder.baseUrl("https://student-helpcare-um.vercel.app/").build().create(
            AuthAPIServices::class.java
        )
    }

    @Provides
    @Singleton
    fun provideSchoolServices(): SchoolAPIServices {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val retrofitBuilder = Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
        return retrofitBuilder.baseUrl("https://student-helpcare-um.vercel.app/").build().create(
            SchoolAPIServices::class.java
        )
    }
}