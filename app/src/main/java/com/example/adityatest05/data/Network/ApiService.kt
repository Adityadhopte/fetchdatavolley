package com.example.adityatest05.data.Network

import com.example.adityatest05.Modelclass
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    companion object{
        const val BASE_URL = "https://api.inopenapp.com/api/v1/"
    }

    @GET("dashboardNew")
    suspend fun getDetails():Response<Modelclass>

}