package com.practiCUM.searchmovie.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.practiCUM.searchmovie.data.NetworkClient
import com.practiCUM.searchmovie.data.dto.MoviesSearchRequest
import com.practiCUM.searchmovie.data.dto.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitNetworkClient(
    private val imdbService: IMDbApi,
    private val context: Context
) : NetworkClient {

//    private val imdbBaseUrl = "https://tv-api.com/"

//    private val retrofit = Retrofit.Builder()
//        .baseUrl(imdbBaseUrl)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()

//    private val imdbService = retrofit.create(IMDbApi::class.java)

    override fun doRequest(dto: Any): Response {
        if (isConnected() == false) {
            return Response().apply { resultCode = -1 }
        }
        if (dto !is MoviesSearchRequest) {
            return Response().apply { resultCode = 400 }
        }

        val response = imdbService.findMovies(dto.expression).execute()
        val body = response.body()
        return if (body != null) {
            body.apply { resultCode = response.code() }
        } else {
            Response().apply { resultCode = response.code() }
        }
    }

    private fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
            }
        }
        return false
    }
}