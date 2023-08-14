package com.relevanx.tcom.api

import com.google.gson.annotations.SerializedName

data class TournamentRequest(

	@field:SerializedName("lokasi")
	val lokasi: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("jenis")
	val jenis: String? = null
)
