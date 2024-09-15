package com.practiCUM.searchmovie.ui.names

import androidx.lifecycle.MutableLiveData
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.practiCUM.searchmovie.domain.api.NamesInteractor
import com.practiCUM.searchmovie.domain.models.Person
import com.practiCUM.searchmovie.R
import com.practiCUM.searchmovie.ui.names.models.NamesState
import com.practiCUM.searchmovie.util.SingleLiveEvent
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay
import androidx.lifecycle.viewModelScope

class NamesViewModel(private val context: Context,
                     private val namesInteractor: NamesInteractor
) : ViewModel() {

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
    }

    private val stateLiveData = MutableLiveData<NamesState>()
    fun observeState(): LiveData<NamesState> = stateLiveData

    private val showToast = SingleLiveEvent<String?>()
    fun observeShowToast(): LiveData<String?> = showToast

    private var latestSearchText: String? = null

    private var searchJob: Job? = null

    fun searchDebounce(changedText: String) {
        if (latestSearchText == changedText) {
            return
        }

        latestSearchText = changedText

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(SEARCH_DEBOUNCE_DELAY)
            searchRequest(changedText)
        }
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            renderState(NamesState.Loading)

            viewModelScope.launch {
                namesInteractor
                    .searchNames(newSearchText)
                    .collect { pair ->
                        processResult(pair.first, pair.second)
                    }
            }
        }
    }

    private fun processResult(foundNames: List<Person>?, errorMessage: String?) {
        val persons = mutableListOf<Person>()
        if (foundNames != null) {
            persons.addAll(foundNames)
        }

        when {
            errorMessage != null -> {
                renderState(
                    NamesState.Error(
                        message = context.getString(
                                R.string.something_went_wrong
                            ),
                    )
                )
                showToast.postValue(errorMessage)
            }
            persons.isEmpty() -> {
                renderState(
                    NamesState.Empty(
                        message = context.getString(R.string.nothing_found),
                    )
                )
            }

            else -> {
                renderState(
                    NamesState.Content(
                        persons = persons,
                    )
                )
            }
        }
    }

    private fun renderState(state: NamesState) {
        stateLiveData.postValue(state)
    }
}