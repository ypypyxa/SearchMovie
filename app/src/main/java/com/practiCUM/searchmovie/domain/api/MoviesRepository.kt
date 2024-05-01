package com.practiCUM.searchmovie.domain.api

import com.practiCUM.searchmovie.domain.models.Movie

interface MoviesRepository {
    fun searchMovies(expression: String): List<Movie>
}