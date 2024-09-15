package com.practiCUM.searchmovie.data.network

import com.practiCUM.searchmovie.data.dto.MovieCastResponse
import com.practiCUM.searchmovie.data.dto.MovieDetailsResponse
import com.practiCUM.searchmovie.data.dto.MoviesResponse
import com.practiCUM.searchmovie.data.dto.NamesSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApi {
    @GET("/RU/API/SearchMovie/k_zcuw1ytf/{expression}")
    suspend fun findMovies(@Path("expression") expression: String): MoviesResponse

    @GET("/RU/API/Title/k_zcuw1ytf/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") movieId: String): MovieDetailsResponse

    @GET("/RU/API/FullCast/k_zcuw1ytf/{movie_id}")
    suspend fun getFullCast(@Path("movie_id") movieId: String): MovieCastResponse

    @GET("/RU/API/SearchName/k_zcuw1ytf/{expression}")
    suspend fun searchNames(@Path("expression") expression: String): NamesSearchResponse
}