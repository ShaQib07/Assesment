package com.shakib.assesment.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.shakib.assesment.model.ApiResponse
import com.shakib.assesment.network.ApiService
import com.shakib.assesment.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object Repository {

    private var mResponse = MutableLiveData<ApiResponse>()

    fun fetchUsers(page: Int) {

        val retrofit = RetrofitClient.buildService(ApiService::class.java)

        val getUsers = retrofit.getUsers(page)

        getUsers.enqueue(object : Callback<ApiResponse>{
            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                Log.e("GSk", t.message!!)
            }

            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful){
                    Log.e("GSk", "Repo - Page $page")
                    mResponse.postValue(response.body())
                }
            }
        })


    }

    fun observeResponse() = mResponse
}