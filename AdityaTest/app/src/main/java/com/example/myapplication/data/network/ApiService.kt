package com.example.myapplication.data.network

import com.example.myapplication.data.ServerResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    companion object{
        const val BASE_URL = "https://api.inopenapp.com/api/v1/"
    }

    @GET("dashboardNew")
    suspend fun getDetails():Response<ServerResponse>

}