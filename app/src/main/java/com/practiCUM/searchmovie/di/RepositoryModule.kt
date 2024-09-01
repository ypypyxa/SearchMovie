package com.practiCUM.searchmovie.di

import com.practiCUM.searchmovie.data.MoviesRepositoryImpl
import com.practiCUM.searchmovie.data.converters.MovieCastConverter
import com.practiCUM.searchmovie.domain.api.MoviesRepository
import com.practiCUM.searchmovie.domain.api.NamesRepository
import com.practiCUM.searchmovie.data.NamesRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    // Добавили фабрику для конвертера
    factory { MovieCastConverter() }

    single<MoviesRepository> {
        MoviesRepositoryImpl(get(), get(), get())
    }

    single<NamesRepository> {
        NamesRepositoryImpl(get())
    }
}