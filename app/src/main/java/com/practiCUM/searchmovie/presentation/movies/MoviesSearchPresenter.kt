package com.practiCUM.searchmovie.presentation.movies

import android.content.Context
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import com.practiCUM.searchmovie.util.Creator
import com.practiCUM.searchmovie.R
import com.practiCUM.searchmovie.domain.api.MoviesInteractor
import com.practiCUM.searchmovie.domain.models.Movie
import com.practiCUM.searchmovie.ui.movies.MoviesAdapter

class MoviesSearchPresenter(
    private val adapter: MoviesAdapter,
    private val context: Context,
    private val view: MoviesView
) {

    private val moviesInteractor = Creator.provideMoviesInteractor(context)
    private val handler = Handler(Looper.getMainLooper())

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
            view.showPlaceholderMessage(false)
            view.showMoviesList(false)
            view.showProgressBar(true)

            moviesInteractor.searchMovies(
                newSearchText,
                object : MoviesInteractor.MoviesConsumer {
                    override fun consume(foundMovie: List<Movie>?, errorMessage: String?) {
                        handler.post {
                            view.showProgressBar(false)
                            if (foundMovie != null) {
                                movies.clear()
                                movies.addAll(foundMovie)
                                adapter.notifyDataSetChanged()
                                view.showMoviesList(true)
                            }
                            if (errorMessage != null) {
                                showMessage(context.getString(R.string.something_went_wrong), errorMessage)
                            } else if (movies.isEmpty()) {
                                showMessage(context.getString(R.string.nothing_found), "")
                            } else {
                                hideMessage()
                            }
                        }
                    }
                }
            )
        }
    }

    companion object {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()
    }

    private val movies = ArrayList<Movie>()


    fun onCreate() {

        adapter.movies = movies

    }

    fun onDestroy() {
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
    }



    private fun showMessage(text: String, additionalMessage: String) {
        if (text.isNotEmpty()) {
            view.showPlaceholderMessage(true)
            movies.clear()
            adapter.notifyDataSetChanged()
            view.changePlaceholderText(text)
            if (additionalMessage.isNotEmpty()) {
                Toast.makeText(context, additionalMessage, Toast.LENGTH_LONG)
                    .show()
            }
        } else {
            view.showPlaceholderMessage(false)
        }
    }

    private fun hideMessage() {
        view.showPlaceholderMessage(false)
    }
}