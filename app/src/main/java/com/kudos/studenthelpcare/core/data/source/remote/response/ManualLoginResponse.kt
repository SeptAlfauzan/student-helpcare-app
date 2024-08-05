package com.kudos.studenthelpcare.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ManualLoginResponse(

	@field:SerializedName("data")
	val data: Data? = null
)

data class Data(

	@field:SerializedName("token")
	val token: String? = null
)
