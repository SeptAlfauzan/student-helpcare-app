package com.kudos.studenthelpcare.core.domain.entities


data class Report(
    val id: String,
    val title: String,
    val desc: String,
    val imageUrl: String?,
    val comments: List<Comment>
)
