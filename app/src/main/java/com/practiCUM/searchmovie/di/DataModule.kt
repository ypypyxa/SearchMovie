package com.practiCUM.searchmovie.di

import android.content.Context
import com.google.gson.Gson
import com.practiCUM.searchmovie.data.NetworkClient
import com.practiCUM.searchmovie.data.SharedPrefences.LocalStorage
import com.practiCUM.searchmovie.data.network.IMDbApi
import com.practiCUM.searchmovie.data.network.RetrofitNetworkClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {

    single<IMDbApi> {
        Retrofit.Builder()
            .baseUrl("https://tv-api.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IMDbApi::class.java)
    }

    single {
        androidContext()
            .getSharedPreferences("local_storage", Context.MODE_PRIVATE)
    }

    factory { Gson() }

    single {
        LocalStorage(get())
    }

    single<NetworkClient> {
        RetrofitNetworkClient(get(), androidContext())
    }

}