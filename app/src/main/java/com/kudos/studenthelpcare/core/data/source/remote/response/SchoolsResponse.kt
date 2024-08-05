package com.kudos.studenthelpcare.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SchoolsResponse(

	@field:SerializedName("data")
	val data: List<Complain?>? = null
)

data class DataItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)
