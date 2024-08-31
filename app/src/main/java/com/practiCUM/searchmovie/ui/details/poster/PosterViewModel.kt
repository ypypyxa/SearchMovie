package com.practiCUM.searchmovie.ui.details.poster

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData

class PosterViewModel(private val url: String) : ViewModel() {

    private val urlLiveData = MutableLiveData(url)
    fun observeUrl(): LiveData<String> = urlLiveData
}