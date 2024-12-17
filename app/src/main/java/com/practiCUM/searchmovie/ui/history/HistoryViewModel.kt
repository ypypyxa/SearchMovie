package com.practiCUM.searchmovie.ui.history

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practiCUM.searchmovie.R
import com.practiCUM.searchmovie.domain.db.HistoryInteractor
import com.practiCUM.searchmovie.domain.models.Movie
import com.practiCUM.searchmovie.ui.history.models.HistoryState
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val context: Context,
    private val historyInteractor: HistoryInteractor
) : ViewModel() {

    private val stateLiveData = MutableLiveData<HistoryState>()

    fun observeState(): LiveData<HistoryState> = stateLiveData

    fun fillData() {
        renderState(HistoryState.Loading)
        viewModelScope.launch {
            historyInteractor
                .historyMovies()
                .collect { movies ->
                    processResult(movies)
                }
        }
    }

    private fun processResult(movies: List<Movie>) {
        if (movies.isEmpty()) {
            renderState(HistoryState.Empty(context.getString(R.string.nothing_searched_yet)))
        } else {
            renderState(HistoryState.Content(movies))
        }
    }

    private fun renderState(state: HistoryState) {
        stateLiveData.postValue(state)
    }
}