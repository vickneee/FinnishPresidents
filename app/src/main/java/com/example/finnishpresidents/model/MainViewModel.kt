package com.example.finnishpresidents.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.finnishpresidents.data.DataProvider
import com.example.finnishpresidents.data.President

class MainViewModel : ViewModel() {

    var presidents = mutableStateListOf<President>()

    init {
        getPresidents()
    }

    private fun getPresidents() {
        presidents.clear()
        presidents.addAll(DataProvider.presidents)
    }
}
