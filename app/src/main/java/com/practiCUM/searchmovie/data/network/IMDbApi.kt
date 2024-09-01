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
    fun findMovies(@Path("expression") expression: String): Call<MoviesResponse>

    @GET("/RU/API/Title/k_zcuw1ytf/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: String): Call<MovieDetailsResponse>

    @GET("/RU/API/FullCast/k_zcuw1ytf/{movie_id}")
    fun getFullCast(@Path("movie_id") movieId: String): Call<MovieCastResponse>

    @GET("/RU/API/SearchName/k_zcuw1ytf/{expression}")
    fun searchNames(@Path("expression") expression: String): Call<NamesSearchResponse>
}