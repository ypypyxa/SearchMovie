package com.practiCUM.searchmovie.ui.movies.models

import com.practiCUM.searchmovie.domain.models.Movie

sealed interface MoviesState {
    object Loading : MoviesState

    data class Content(
        val movies: List<Movie>
    ) : MoviesState

    data class Error(
        val errorMessage: String
    ) : MoviesState

    data class Empty(
        val message: String
    ) : MoviesState
}
