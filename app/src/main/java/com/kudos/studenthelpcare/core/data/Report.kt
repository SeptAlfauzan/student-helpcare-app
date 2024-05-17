package com.kudos.studenthelpcare.core.data


data class Report(
    val id: String,
    val title: String,
    val desc: String,
    val imageUrl: String?,
    val comments: List<Comment>
)
