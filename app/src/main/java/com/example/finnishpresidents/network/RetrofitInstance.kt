package com.example.finnishpresidents.network

import com.example.finnishpresidents.data.api.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue
import kotlin.jvm.java

object RetrofitInstance {

    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    // Retrofit instance
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}