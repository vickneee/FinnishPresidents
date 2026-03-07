package com.example.finnishpresidents.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finnishpresidents.data.DataProvider
import com.example.finnishpresidents.data.President
import com.example.finnishpresidents.repository.WikiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repository: WikiRepository = WikiRepository()
    var presidents = mutableStateListOf<President>()
    var wikiUiState = mutableStateMapOf<String, Int>()
        private set
    var loadingState = mutableStateMapOf<String, Boolean>() // Track loading per president

    init {
        presidents.addAll(DataProvider.presidents)
    }

    fun getHits(name: String) {
        loadingState[name] = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val serverResp = repository.hitCountCheck(name)
                wikiUiState[name] = serverResp.query.searchinfo.totalhits
            } catch (e: Exception) {
                e.printStackTrace()
                wikiUiState[name] = -1
            } finally {
                loadingState[name] = false
            }
        }
    }
}