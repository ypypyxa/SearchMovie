package com.practiCUM.searchmovie.domain.api

import com.practiCUM.searchmovie.domain.models.Movie
import com.practiCUM.searchmovie.domain.models.MovieCast
import com.practiCUM.searchmovie.domain.models.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MoviesInteractor {
    fun searchMovies(expression: String): Flow<Pair<List<Movie>?, String?>>
    fun addMovieToFavorites(movie: Movie)
    fun removeMovieFromFavorites(movie: Movie)
    fun getMovieDetails(movieId: String): Flow<Pair<MovieDetails?, String?>>
    fun getMovieCast(movieId: String): Flow<Pair<MovieCast?, String?>>
}