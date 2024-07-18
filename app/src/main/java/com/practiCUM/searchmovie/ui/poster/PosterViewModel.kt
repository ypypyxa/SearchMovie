package com.practiCUM.searchmovie.ui.poster

import SingleLiveEvent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class PosterViewModel(private val url: String) : ViewModel() {

    private val setupPoster = SingleLiveEvent<String>()
    fun observeSetupPoster(): LiveData<String> = setupPoster

    init {
        setupPoster.postValue(url)
    }

}