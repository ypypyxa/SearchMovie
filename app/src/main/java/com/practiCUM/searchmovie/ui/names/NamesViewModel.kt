package com.practiCUM.searchmovie.ui.names

import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.practiCUM.searchmovie.domain.api.NamesInteractor
import com.practiCUM.searchmovie.domain.models.Person
import com.practiCUM.searchmovie.R
import com.practiCUM.searchmovie.ui.names.models.NamesState
import com.practiCUM.searchmovie.util.SingleLiveEvent

class NamesViewModel(private val context: Context,
                     private val namesInteractor: NamesInteractor
) : ViewModel() {

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()
    }

    private val handler = Handler(Looper.getMainLooper())

    private val stateLiveData = MutableLiveData<NamesState>()
    fun observeState(): LiveData<NamesState> = stateLiveData

    private val showToast = SingleLiveEvent<String?>()
    fun observeShowToast(): LiveData<String?> = showToast

    private var latestSearchText: String? = null

    override fun onCleared() {
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
    }

    fun searchDebounce(changedText: String) {
        if (latestSearchText == changedText) {
            return
        }

        this.latestSearchText = changedText
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)

        val searchRunnable = Runnable { searchRequest(changedText) }

        val postTime = SystemClock.uptimeMillis() + SEARCH_DEBOUNCE_DELAY
        handler.postAtTime(
            searchRunnable,
            SEARCH_REQUEST_TOKEN,
            postTime,
        )
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            renderState(NamesState.Loading)

            namesInteractor.searchNames(newSearchText, object : NamesInteractor.NamesConsumer {
                override fun consume(foundNames: List<Person>?, errorMessage: String?) {
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
            })
        }
    }

    private fun renderState(state: NamesState) {
        stateLiveData.postValue(state)
    }
}