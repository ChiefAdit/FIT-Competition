package com.relevanx.tcom.activity.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.relevanx.tcom.api.ApiConfig
import com.relevanx.tcom.api.OrderRequest
import com.relevanx.tcom.api.OrderResponse

class OrderViewModel : ViewModel() {
    private val _order = MutableLiveData<OrderResponse>()
    val order: MutableLiveData<OrderResponse> = _order

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun getOrderResponse(uid: String){
        _isLoading.postValue(true)
        val client = ApiConfig.getApiService()
        client.getOrder(OrderRequest(uid)).enqueue(object : retrofit2.Callback<OrderResponse> {
            override fun onResponse(
                call: retrofit2.Call<OrderResponse>,
                response: retrofit2.Response<OrderResponse>
            ) {
                if (response.isSuccessful) {
                    _isLoading.postValue(false)
                    _order.value = response.body()
                } else{
                    _isLoading.postValue(false)
                }
            }

            override fun onFailure(call: retrofit2.Call<OrderResponse>, t: Throwable) {
                _isLoading.postValue(false)
                t.printStackTrace()
            }
        })
    }
}