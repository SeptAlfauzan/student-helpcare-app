package com.kudos.studenthelpcare.core.data.source.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.gson.Gson
import com.kudos.studenthelpcare.core.domain.entities.LoginBody
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataStorePreference @Inject constructor(@ApplicationContext private val context: Context) {
    private val AUTH_TOKEN = stringPreferencesKey("LOGIN_TOKEN")
    private val FIRST_OPEN = booleanPreferencesKey("FIRST_OPEN")
    private val PREVIOUS_LOGIN = stringPreferencesKey("PREVIOUS_LOGIN")
    fun getAuthToken(): Flow<String> = context.datastore.data.map { it[AUTH_TOKEN] ?: "" }
    suspend fun setAuthToken(token: String) = context.datastore.edit { it[AUTH_TOKEN] = token }

    fun getPreviousLoginData(): Flow<LoginBody?> = context.datastore.data.map {
        try {
            val gson = Gson()
            val loginData: LoginBody? = gson.fromJson(it[AUTH_TOKEN], LoginBody::class.java)
            loginData
        } catch (e: Exception) {
            null
        }
    }

    suspend fun setPreviousLoginData(loginBody: LoginBody) = context.datastore.edit {
        val gson = Gson()
        val json = gson.toJson(loginBody)
        it[AUTH_TOKEN] = json
    }

    companion object {
        @Volatile
        var INSTANCE: DataStorePreference? = null
        fun getInstances(context: Context): DataStorePreference = INSTANCE ?: synchronized(this) {
            val instance = DataStorePreference(context)
            INSTANCE = instance
            instance
        }
    }
}