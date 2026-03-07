package com.example.finnishpresidents.data.api

import com.example.finnishpresidents.repository.WikiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api.php")
    suspend fun searchWikipedia(
        @Query("action") action: String = "query",
        @Query("list") list: String = "search",
        @Query("srsearch") srsearch: String,
        @Query("format") format: String = "json"
    ): WikiResponse
}