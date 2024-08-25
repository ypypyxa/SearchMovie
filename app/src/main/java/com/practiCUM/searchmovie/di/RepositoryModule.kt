package com.practiCUM.searchmovie.di

import com.practiCUM.searchmovie.data.MoviesRepositoryImpl
import com.practiCUM.searchmovie.domain.api.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<MoviesRepository> {
        MoviesRepositoryImpl(get(), get())
    }

}