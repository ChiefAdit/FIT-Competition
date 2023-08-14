package com.relevanx.tcom.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class OrderResponse(

	@field:SerializedName("orders")
	val orders: List<OrdersItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
)

@Parcelize
data class OrdersItem(

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

	@field:SerializedName("tournament_id")
	val tournamentId: Int? = null,

	@field:SerializedName("user_id")
	val userId: String? = null,

	@field:SerializedName("lokasi")
	val lokasi: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("jenis")
	val jenis: String? = null,

	@field:SerializedName("linkgroup")
	val linkgroup: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("anggota_team")
	val anggotaTeam: Int? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("status")
	val status: String? = null
): Parcelable
