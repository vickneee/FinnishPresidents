package com.example.finnishpresidents.data.api
import com.example.finnishpresidents.data.President
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    suspend fun getPosts(): List<President>
}