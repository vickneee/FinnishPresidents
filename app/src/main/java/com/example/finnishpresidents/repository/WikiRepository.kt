package com.example.finnishpresidents.repository

import com.example.finnishpresidents.network.RetrofitInstance

class WikiRepository {
    suspend fun hitCountCheck(name: String): WikiResponse {
        return RetrofitInstance.api.searchWikipedia(srsearch = name)
    }
}