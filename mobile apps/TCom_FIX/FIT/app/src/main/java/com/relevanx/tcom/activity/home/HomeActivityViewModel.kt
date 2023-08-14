package com.relevanx.tcom.activity.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.relevanx.tcom.api.ApiConfig
import com.relevanx.tcom.api.LoginRequest
import com.relevanx.tcom.api.NewsResponse
import com.relevanx.tcom.api.OrderRequest
import com.relevanx.tcom.api.OrderResponse
import com.relevanx.tcom.api.TournamentRequest
import com.relevanx.tcom.api.TournamentResponse
import okhttp3.ResponseBody

class HomeActivityViewModel : ViewModel() {
    private val _news = MutableLiveData<NewsResponse>()
    val news: MutableLiveData<NewsResponse> = _news

    private val _login = MutableLiveData<Boolean>()
    val login: MutableLiveData<Boolean> = _login

    private val _order = MutableLiveData<OrderResponse>()
    val order: MutableLiveData<OrderResponse> = _order

    private val _tournament = MutableLiveData<TournamentResponse>()
    val tournament: MutableLiveData<TournamentResponse> = _tournament

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getTournament(){
        _isLoading.postValue(true)
        val client = ApiConfig.getApiService()
        client.getTournament(TournamentRequest("","","")).enqueue(object : retrofit2.Callback<TournamentResponse> {
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

    private val _isLoading2= MutableLiveData<Boolean>()
    val isLoading2: LiveData<Boolean> = _isLoading2

    fun getNews(){
        _isLoading2.postValue(true)
        val client = ApiConfig.getApiService()
        client.getNews().enqueue(object : retrofit2.Callback<NewsResponse> {
            override fun onResponse(
                call: retrofit2.Call<NewsResponse>,
                response: retrofit2.Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.postValue(false)
                    _news.value = response.body()
                }
            }

            override fun onFailure(call: retrofit2.Call<NewsResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }


    private val _isLoading3= MutableLiveData<Boolean>()
    val isLoading3: LiveData<Boolean> = _isLoading3

    fun postLogin(uid: String){
        _isLoading3.postValue(true)
        val client = ApiConfig.getApiService()
        client.login(LoginRequest(uid)).enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onResponse(
                call: retrofit2.Call<ResponseBody>,
                response: retrofit2.Response<ResponseBody>
            ) {
                if (response.isSuccessful) {
                    _isLoading.postValue(false)
                    _login.value = response.isSuccessful
                    Log.d("Login Value", "onResponse: ${response.code()}")
                }
            }

            override fun onFailure(call: retrofit2.Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun getOrderResponse(uid: String){
        val client = ApiConfig.getApiService()
        client.getOrder(OrderRequest(uid)).enqueue(object : retrofit2.Callback<OrderResponse> {
            override fun onResponse(
                call: retrofit2.Call<OrderResponse>,
                response: retrofit2.Response<OrderResponse>
            ) {
                if (response.isSuccessful) {
                    _order.value = response.body()
                }
            }

            override fun onFailure(call: retrofit2.Call<OrderResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

}