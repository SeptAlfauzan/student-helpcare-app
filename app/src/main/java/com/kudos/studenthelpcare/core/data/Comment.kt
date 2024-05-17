package com.kudos.studenthelpcare.core.data


data class Comment(
    val id: String,
    val userProfile: UserProfile,
    val text: String,
    val created: String,
)