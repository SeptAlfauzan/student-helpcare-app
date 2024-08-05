package com.kudos.studenthelpcare.core.di.modules

import com.kudos.studenthelpcare.core.data.source.remote.services.StudentHelpcareAPIServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
    fun provideOkHttpClient(
//        firebaseTokenInterceptor: FirebaseTokenInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
//        .addInterceptor(firebaseTokenInterceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())

    @Provides
    @Singleton
    fun provideStudentHelpcareAPIServices(retrofitBuilder: Retrofit.Builder): StudentHelpcareAPIServices  = retrofitBuilder.baseUrl("https://student-helpcare-um.vercel.app/").build().create(
        StudentHelpcareAPIServices::class.java
    )
}