package com.kudos.studenthelpcare.core.domain.entities

import com.google.gson.annotations.SerializedName

data class RegisterBody(
    val name: String,
    val email: String,
    val password: String,
    @SerializedName("id_school") val idSchool: String,
)