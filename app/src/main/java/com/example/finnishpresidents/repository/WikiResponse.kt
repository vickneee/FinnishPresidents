package com.example.finnishpresidents.repository

data class WikiResponse(
    val query: WikiQuery
)

data class WikiQuery(
    val searchinfo: WikiSearchInfo
)

data class WikiSearchInfo(
    val totalhits: Int
)