package com.practiCUM.searchmovie.data.network

import com.practiCUM.searchmovie.data.dto.MovieCastResponse
import com.practiCUM.searchmovie.data.dto.MovieDetailsResponse
import com.practiCUM.searchmovie.data.dto.MoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMDbApi {
    @GET("/en/API/SearchMovie/k_zcuw1ytf/{expression}")
    fun findMovies(@Path("expression") expression: String): Call<MoviesResponse>

    @GET("/en/API/Title/k_zcuw1ytf/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: String): Call<MovieDetailsResponse>

    @GET("/en/API/FullCast/k_zcuw1ytf/{movie_id}")
    fun getFullCast(@Path("movie_id") movieId: String): Call<MovieCastResponse>
}