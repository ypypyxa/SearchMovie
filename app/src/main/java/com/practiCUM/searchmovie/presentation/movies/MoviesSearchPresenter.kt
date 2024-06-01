package com.practiCUM.searchmovie.presentation.movies

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import com.practiCUM.searchmovie.util.Creator
import com.practiCUM.searchmovie.R
import com.practiCUM.searchmovie.domain.api.MoviesInteractor
import com.practiCUM.searchmovie.domain.models.Movie
import com.practiCUM.searchmovie.ui.movies.models.MoviesState

class MoviesSearchPresenter(
    private val context: Context,
    private val view: MoviesView
) {

    private val moviesInteractor = Creator.provideMoviesInteractor(context)
    private val handler = Handler(Looper.getMainLooper())

    private val movies = ArrayList<Movie>()

    fun searchDebounce(changedText: String) {
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
            view.render(
                MoviesState.Loading
            )

            moviesInteractor.searchMovies(newSearchText, object : MoviesInteractor.MoviesConsumer {
                override fun consume(foundMovie: List<Movie>?, errorMessage: String?) {
                    handler.post {
                        if (foundMovie != null) {
                            movies.clear()
                            movies.addAll(foundMovie)
                        }

                        when {
                            errorMessage != null -> {
                                view.render(
                                    MoviesState.Error(
                                        errorMessage = context.getString(R.string.something_went_wrong)
                                    )
                                )
                                view.showToast(errorMessage)
                            }
                            movies.isEmpty() -> {
                                view.render(
                                    MoviesState.Empty(
                                        message = context.getString(R.string.nothing_found)
                                    )
                                )
                            }
                            else -> {
                                view.render(
                                    MoviesState.Content(
                                        movies = movies
                                    )
                                )
                            }
                        }
                    }
                }
            } )
        }
    }

    fun onDestroy() {
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()
    }
}