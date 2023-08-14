package com.relevanx.tcom.api

import com.google.gson.annotations.SerializedName

data class LoginRequest(

	@field:SerializedName("id")
	val id: String? = null
)
