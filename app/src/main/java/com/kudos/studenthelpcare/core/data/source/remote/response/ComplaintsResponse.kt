package com.kudos.studenthelpcare.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ComplaintsResponse(

	@field:SerializedName("data")
	val data: List<Complain?>? = null
)

data class Complain(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("is_responded")
	val isResponded: Boolean? = null,

	@field:SerializedName("comment")
	val comment: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("id_user")
	val idUser: String? = null
)
