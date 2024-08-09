package com.kudos.studenthelpcare.core.utils

import com.kudos.studenthelpcare.core.data.source.remote.response.ComplaintsResponse
import com.kudos.studenthelpcare.core.domain.Mapper
import com.kudos.studenthelpcare.core.domain.entities.Comment
import com.kudos.studenthelpcare.core.domain.entities.Complaint
import com.kudos.studenthelpcare.core.domain.entities.UserProfile

object ComplaintResponseMapper : Mapper<ComplaintsResponse, List<Complaint>> {
    override fun map(from: ComplaintsResponse): List<Complaint> {
        return from.data?.filterNotNull()?.map {

            Complaint(
                id = it.id ?: "",
                desc = it.createdAt ?: "-",
                title = it.description ?: "-",
                imageUrl = null,
                isResponded = it.isResponded ?: false,
                comments = if(it.comment != null) listOf(
                    Comment(
                        id = "",
                        created = it.updatedAt ?: "-",
                        text = it.comment,
                        userProfile = UserProfile(
                            id = "",
                            name = "Admin",
                            username = "admin@admin.com",
                            schoolId = "",
                            imageUrl = ""
                        )
                    )
                ) else listOf(),
            )
        }?.toList() ?: listOf()
    }
}