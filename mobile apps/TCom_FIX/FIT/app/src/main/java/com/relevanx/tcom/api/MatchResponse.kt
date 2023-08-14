package com.relevanx.tcom.api

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class MatchResponse(

	@field:SerializedName("matches")
	val matches: List<MatchesItem?>? = null
)

@Parcelize
data class TeamsItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("gambar")
	val gambar: String? = null,

	@field:SerializedName("point")
	val point: String? = null
): Parcelable

@Parcelize
data class MatchesItem(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("teams")
	val teams: List<TeamsItem?>? = null,

	@field:SerializedName("bracket")
	val bracket: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("time")
	val time: String? = null
) : Parcelable
