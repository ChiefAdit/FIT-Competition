package com.relevanx.tcom.api

import com.google.gson.annotations.SerializedName

data class OrderMatchRequest(

	@field:SerializedName("tournamen_id")
	val tournamenId: Int? = null
)
