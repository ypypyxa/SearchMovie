package com.practiCUM.searchmovie

import android.app.Application
import com.practiCUM.searchmovie.di.dataModule
import com.practiCUM.searchmovie.di.interactorModule
import com.practiCUM.searchmovie.di.navigationModule
import com.practiCUM.searchmovie.di.repositoryModule
import com.practiCUM.searchmovie.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MoviesApplication)
            modules(dataModule, repositoryModule, interactorModule, viewModelModule, navigationModule)
        }
    }
}