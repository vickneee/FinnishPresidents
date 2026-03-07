package com.example.finnishpresidents.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
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

    var wikiUiState: Int by mutableIntStateOf(0)
        private set

    init {
        getPresidents()
    }

    private fun getPresidents() {
        presidents.clear()
        presidents.addAll(DataProvider.presidents)
    }

    fun getHits(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val serverResp = repository.hitCountCheck(name)
                wikiUiState = serverResp.query.searchinfo.totalhits
            } catch (e: Exception) {
                e.printStackTrace()
                wikiUiState = -1
            }
        }
    }

    fun fetchWikiHits(presidentName: String, onResult: (Int) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = repository.hitCountCheck(presidentName)
                onResult(response.query.searchinfo.totalhits)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult(-1)
            }
        }
    }
}