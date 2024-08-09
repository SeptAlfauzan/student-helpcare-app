package com.kudos.studenthelpcare.core.domain.entities


data class Complaint(
    val id: String,
    val title: String,
    val desc: String,
    val imageUrl: String?,
    val comments: List<Comment>,
    val isResponded: Boolean
)
