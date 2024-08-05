package com.kudos.studenthelpcare.core.domain.entities


data class Comment(
    val id: String,
    val userProfile: UserProfile,
    val text: String,
    val created: String,
)