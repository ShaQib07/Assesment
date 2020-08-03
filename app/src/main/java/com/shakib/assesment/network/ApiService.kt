package com.shakib.assesment.network

import com.shakib.assesment.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    fun getUsers(
        @Query("page") page: Int
    ): Call<ApiResponse>
}