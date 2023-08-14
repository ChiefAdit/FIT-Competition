package com.relevanx.tcom.api

import com.google.gson.annotations.SerializedName

data class MakeOrderRequest(

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("tournamen_id")
	val tournamenId: Int? = null
)
