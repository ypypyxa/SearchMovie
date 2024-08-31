package com.practiCUM.searchmovie.domain.api

import com.practiCUM.searchmovie.domain.models.Movie
import com.practiCUM.searchmovie.domain.models.MovieDetails
import com.practiCUM.searchmovie.util.Resource

interface MoviesRepository {
    fun searchMovies(expression: String): Resource<List<Movie>>
    fun addMovieToFavorites(movie: Movie)
    fun removeMovieFromFavorites(movie: Movie)
    fun getMovieDetails(movieId: String): Resource<MovieDetails>
}