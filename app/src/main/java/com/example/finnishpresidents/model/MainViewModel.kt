package com.example.finnishpresidents.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finnishpresidents.data.DataProvider
import com.example.finnishpresidents.data.President
import com.example.finnishpresidents.network.RetrofitInstance
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    var presidents = mutableStateListOf<President>()

    init {
        getPresidents()
    }

    private fun getPresidents() {
        presidents.clear()
        presidents.addAll(DataProvider.presidents)
    }

    fun fetchWikiHits(presidentName: String, onResult: (Int) -> Unit) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.searchWikipedia(srsearch = presidentName)
                onResult(response.query.searchinfo.totalhits)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(-1) // error
            }
        }
    }
}