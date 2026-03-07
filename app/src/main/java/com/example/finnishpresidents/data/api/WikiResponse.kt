package com.example.finnishpresidents.data.api

data class WikiResponse(
    val query: WikiQuery
)

data class WikiQuery(
    val searchinfo: WikiSearchInfo
)

data class WikiSearchInfo(
    val totalhits: Int
)