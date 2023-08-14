package com.relevanx.tcom.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("getnews")
    fun getNews(): Call<NewsResponse>

//    @GET("getalltournamen")
//    fun getTournament(): Call<TournamentResponse>

    @POST("filtertournamen")
    fun getTournament(
        @Body tournamentRequest: TournamentRequest
    ): Call<TournamentResponse>

    @POST("loginCustomer")
    fun login(
        @Body loginRequest: LoginRequest
    ): Call<ResponseBody>

    @POST("getorderbyid")
    fun getOrder(
        @Body orderRequest: OrderRequest
    ): Call<OrderResponse>

    @POST("partisipan")
    fun postPartisipan(
        @Body partisipanRequest: TeamRequest
    ): Call<ResponseBody>

    @POST("makeorder")
    fun postMakeOrder(
        @Body orderRequest: MakeOrderRequest
    ): Call<ResponseBody>

    @POST("getallmatcheswithteamsandpoints")
    fun getMatch(
        @Body matchRequest: OrderMatchRequest
    ): Call<MatchResponse>

}