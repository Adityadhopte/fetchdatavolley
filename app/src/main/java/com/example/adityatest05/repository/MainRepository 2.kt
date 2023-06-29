package com.example.myapplication.data.repository

import com.example.adityatest05.data.Network.ApiService
import com.example.myapplication.utils.toResultFlow
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {

    fun getDetails() = toResultFlow {
        apiService.getDetails()
    }

}