package com.relevanx.tcom.activity.tournament

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.relevanx.tcom.api.ApiConfig
import com.relevanx.tcom.api.TournamentRequest
import com.relevanx.tcom.api.TournamentResponse

class TournamentViewModel : ViewModel() {
    private val _tournament = MutableLiveData<TournamentResponse>()
    val tournament: MutableLiveData<TournamentResponse> = _tournament

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getTournament(lokasi:String, name:String, jenis:String){
        _isLoading.postValue(true)
        val client = ApiConfig.getApiService()
        client.getTournament(TournamentRequest(lokasi,name,jenis)).enqueue(object : retrofit2.Callback<TournamentResponse> {
            override fun onResponse(
                call: retrofit2.Call<TournamentResponse>,
                response: retrofit2.Response<TournamentResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.postValue(false)
                    _tournament.value = response.body()
                }
            }

            override fun onFailure(call: retrofit2.Call<TournamentResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}