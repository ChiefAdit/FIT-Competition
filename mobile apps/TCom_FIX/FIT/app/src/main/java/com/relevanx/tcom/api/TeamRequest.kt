package com.relevanx.tcom.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamRequest(

	@field:SerializedName("id_tournamen")
	val idTournamen: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id_user")
	val idUser: String? = null,

	@field:SerializedName("no_telp")
	val noTelp: String? = null,

	@field:SerializedName("gambar")
	val gambar: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null
): Parcelable
