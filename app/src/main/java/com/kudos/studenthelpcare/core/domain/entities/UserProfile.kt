package com.kudos.studenthelpcare.core.domain.entities


data class UserProfile(
    val id: String,
    val imageUrl: String,
    val name: String,
    val schoolId: String,
    val username: String,
)
