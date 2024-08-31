package com.practiCUM.searchmovie.data

import com.practiCUM.searchmovie.data.SharedPrefences.LocalStorage
import com.practiCUM.searchmovie.data.converters.MovieCastConverter
import com.practiCUM.searchmovie.data.dto.MovieCastResponse
import com.practiCUM.searchmovie.data.dto.MovieCastRequest
import com.practiCUM.searchmovie.data.dto.MovieDetailsResponse
import com.practiCUM.searchmovie.data.dto.MovieDetailsRequest
import com.practiCUM.searchmovie.data.dto.MoviesResponse
import com.practiCUM.searchmovie.data.dto.MoviesSearchRequest
import com.practiCUM.searchmovie.domain.api.MoviesRepository
import com.practiCUM.searchmovie.domain.models.Movie
import com.practiCUM.searchmovie.domain.models.MovieCast
import com.practiCUM.searchmovie.domain.models.MovieDetails
import com.practiCUM.searchmovie.util.Resource

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val movieCastConverter: MovieCastConverter,
    private val localStorage: LocalStorage
) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                val stored = localStorage.getSavedFavorites()

                Resource.Success((response as MoviesResponse).results.map {
                    Movie(
                        id = it.id,
                        resultType = it.resultType,
                        image = it.image,
                        title = it.title,
                        description = it.description,
                        inFavorite = stored.contains(it.id)
                    )
                })
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }

    override fun getMovieDetails(movieId: String): Resource<MovieDetails> {
        val response = networkClient.doRequest(MovieDetailsRequest(movieId))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                with(response as MovieDetailsResponse) {
                    Resource.Success(
                        MovieDetails(
                            id,
                            title,
                            imDbRating,
                            year,
                            countries,
                            genres,
                            directors,
                            writers,
                            stars,
                            plot
                        )
                    )
                }
            }
            else -> {
                Resource.Error("Ошибка сервера")

            }
        }
    }

    override fun getMovieCast(movieId: String): Resource<MovieCast> {
        // Поменяли объект dto на нужный Request-объект
        val response = networkClient.doRequest(MovieCastRequest(movieId))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                // Осталось написать конвертацию!
                with(response as MovieCastResponse) {
                    Resource.Success(
                        data = movieCastConverter.convert(response as MovieCastResponse)
                    )
                }
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }

    override fun addMovieToFavorites(movie: Movie) {
        localStorage.addToFavorites(movie.id)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        localStorage.removeFromFavorites(movie.id)
    }
}