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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val movieCastConverter: MovieCastConverter,
    private val localStorage: LocalStorage
) : MoviesRepository {

    override fun searchMovies(expression: String): Flow<Resource<List<Movie>>> = flow {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }
            200 -> {
                val stored = localStorage.getSavedFavorites()

                emit(Resource.Success((response as MoviesResponse).results.map {
                    Movie(
                        id = it.id,
                        resultType = it.resultType,
                        image = it.image,
                        title = it.title,
                        description = it.description,
                        inFavorite = stored.contains(it.id)
                    )
                }))
            }
            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }

    override fun getMovieDetails(movieId: String): Flow<Resource<MovieDetails>> = flow {
        val response = networkClient.doRequest(MovieDetailsRequest(movieId))
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }
            200 -> {
                with(response as MovieDetailsResponse) {
                    emit(
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
                    )
                }
            }
            else -> {
                emit(Resource.Error("Ошибка сервера"))

            }
        }
    }

    override fun getMovieCast(movieId: String): Flow<Resource<MovieCast>> = flow {
        // Поменяли объект dto на нужный Request-объект
        val response = networkClient.doRequest(MovieCastRequest(movieId))
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }
            200 -> {
                // Осталось написать конвертацию!
                with(response as MovieCastResponse) {
                    emit(Resource.Success(
                        data = movieCastConverter.convert(response as MovieCastResponse)
                    ))
                }
            }
            else -> {
                emit(Resource.Error("Ошибка сервера"))
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