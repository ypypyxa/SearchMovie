package com.practiCUM.searchmovie.presentation.movies

import com.practiCUM.searchmovie.domain.models.Movie

interface MoviesView {
    fun showPlaceholderMessage(isVisible: Boolean)
    fun showMoviesList(isVisible: Boolean)
    fun showProgressBar(isVisible: Boolean)
    fun changePlaceholderText(newPlaceholderText: String)
    fun updateMoviesList(newMoviesList: List<Movie>)
    fun showToast(additionalMessage: String)
}