package com.relevanx.tcom.activity.match

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.relevanx.tcom.api.ApiConfig
import com.relevanx.tcom.api.MatchResponse
import com.relevanx.tcom.api.OrderMatchRequest

class MatchViewModel : ViewModel(){
    private val _match = MutableLiveData<MatchResponse>()
    val match: MutableLiveData<MatchResponse> = _match

    fun getMatchResponse(tournamentId: Int){
        val client = ApiConfig.getApiService()
        client.getMatch(OrderMatchRequest(tournamentId)).enqueue(object : retrofit2.Callback<MatchResponse> {
            override fun onResponse(
                call: retrofit2.Call<MatchResponse>,
                response: retrofit2.Response<MatchResponse>
            ) {
                if (response.isSuccessful) {
                    _match.value = response.body()
                }
            }

            override fun onFailure(call: retrofit2.Call<MatchResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

}