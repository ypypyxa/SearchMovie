package com.practiCUM.searchmovie.presentation.movies

import SingleLiveEvent
import android.app.Application
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.practiCUM.searchmovie.util.Creator
import com.practiCUM.searchmovie.R
import com.practiCUM.searchmovie.domain.api.MoviesInteractor
import com.practiCUM.searchmovie.domain.models.Movie
import com.practiCUM.searchmovie.ui.movies.models.MoviesState
import com.practiCUM.searchmovie.ui.movies.models.ToastState
import moxy.MvpPresenter
import kotlin.math.round

class MoviesSearchViewModel(application: Application) : AndroidViewModel(application) {

    private val moviesInteractor = Creator.provideMoviesInteractor(getApplication())
    private val handler = Handler(Looper.getMainLooper())

    private var latestSearchText: String? = null

    private val stateLiveData = MutableLiveData<MoviesState>()
    fun observeState(): LiveData<MoviesState> = stateLiveData

//    private val toastState = MutableLiveData<ToastState>(ToastState.None)
//    fun observeToastState(): LiveData<ToastState> = toastState

    private val showToast = SingleLiveEvent<String>()
    fun observeShowToast(): LiveData<String> = showToast

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
            postTime
        )
    }

    private fun searchRequest(newSearchText: String) {
        if (newSearchText.isNotEmpty()) {
            renderState(MoviesState.Loading)

            moviesInteractor.searchMovies(newSearchText, object : MoviesInteractor.MoviesConsumer {
                override fun consume(foundMovies: List<Movie>?, errorMessage: String?) {
                    val movies = mutableListOf<Movie>()
                    if (foundMovies != null) {
                        movies.addAll(foundMovies)
                    }

                    when {
                        errorMessage != null -> {
                            renderState(
                                MoviesState.Error(
                                    errorMessage = getApplication<Application>().getString(R.string.something_went_wrong)
                                )
                            )
                            showToast.postValue(errorMessage)
                        }

                        movies.isEmpty() -> {
                            renderState(
                                MoviesState.Empty(
                                    message = getApplication<Application>().getString(R.string.nothing_found)
                                )
                            )
                        }

                        else -> {
                            renderState(
                                MoviesState.Content(
                                    movies = movies
                                )
                            )
                        }
                    }
                }
            })
        }
    }

    private fun renderState(state: MoviesState) {
        stateLiveData.postValue(state)
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()

        fun getViewModelFactory(): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                MoviesSearchViewModel(this[APPLICATION_KEY] as Application)
            }
        }
    }
}