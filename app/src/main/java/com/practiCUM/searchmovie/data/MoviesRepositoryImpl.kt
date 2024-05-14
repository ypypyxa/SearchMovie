package com.practiCUM.searchmovie.data

import com.practiCUM.searchmovie.data.dto.MoviesResponse
import com.practiCUM.searchmovie.data.dto.MoviesSearchRequest
import com.practiCUM.searchmovie.domain.api.MoviesRepository
import com.practiCUM.searchmovie.domain.models.Movie
import com.practiCUM.searchmovie.util.Resource

class MoviesRepositoryImpl(private val networkClient: NetworkClient) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                Resource.Success((response as MoviesResponse).results.map {
                    Movie(it.id, it.resultType, it.image, it.title, it.description)})
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}