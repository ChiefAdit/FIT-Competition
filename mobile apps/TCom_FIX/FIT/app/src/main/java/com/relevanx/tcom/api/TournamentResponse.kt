package com.relevanx.tcom.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class TournamentResponse(

	@field:SerializedName("tournamen")
	val tournamen: List<TournamenItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

@Suppress("DEPRECATED_ANNOTATION")
@Parcelize
data class TournamenItem(

	@field:SerializedName("whatsapp")
	val whatsapp: String? = null,

	@field:SerializedName("fee")
	val fee: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("partisipan")
	val partisipan: Int? = null,

	@field:SerializedName("gambar")
	val gambar: String? = null,

	@field:SerializedName("lokasi")
	val lokasi: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("jenis")
	val jenis: String? = null,

	@field:SerializedName("bracket")
	val bracket: String? = null,

	@field:SerializedName("linkgroup")
	val linkgroup: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable
