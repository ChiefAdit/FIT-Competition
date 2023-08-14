package com.relevanx.tcom.api

import com.google.gson.annotations.SerializedName

data class OrderRequest(

	@field:SerializedName("user_id")
	val userId: String? = null
)
