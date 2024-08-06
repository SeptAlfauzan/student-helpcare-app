package com.kudos.studenthelpcare.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val data: LoginData? = null
)

data class LoginData(

	@field:SerializedName("token")
	val token: String? = null
)
