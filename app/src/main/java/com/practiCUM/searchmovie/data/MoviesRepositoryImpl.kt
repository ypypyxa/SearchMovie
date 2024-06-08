package com.practiCUM.searchmovie.data

import com.practiCUM.searchmovie.data.SharedPrefences.LocalStorage
import com.practiCUM.searchmovie.data.dto.MoviesResponse
import com.practiCUM.searchmovie.data.dto.MoviesSearchRequest
import com.practiCUM.searchmovie.domain.api.MoviesRepository
import com.practiCUM.searchmovie.domain.models.Movie
import com.practiCUM.searchmovie.util.Resource

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
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

    override fun addMovieToFavorites(movie: Movie) {
        localStorage.addToFavorites(movie.id)
    }

    override fun removeMovieFromFavorites(movie: Movie) {
        localStorage.removeFromFavorites(movie.id)
    }
}