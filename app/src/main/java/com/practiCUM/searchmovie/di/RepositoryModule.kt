package com.practiCUM.searchmovie.di

import com.practiCUM.searchmovie.data.HistoryRepositoryImpl
import com.practiCUM.searchmovie.data.MoviesRepositoryImpl
import com.practiCUM.searchmovie.data.converters.MovieCastConverter
import com.practiCUM.searchmovie.domain.api.MoviesRepository
import com.practiCUM.searchmovie.domain.api.NamesRepository
import com.practiCUM.searchmovie.data.NamesRepositoryImpl
import com.practiCUM.searchmovie.data.converters.MovieDbConvertor
import com.practiCUM.searchmovie.domain.db.HistoryRepository
import org.koin.dsl.module

val repositoryModule = module {

    // Добавили фабрику для конвертера
    factory { MovieCastConverter() }

    factory { MovieDbConvertor() }

    single<MoviesRepository> {
        MoviesRepositoryImpl(get(), get(), get(), get(), get())
    }

    single<NamesRepository> {
        NamesRepositoryImpl(get())
    }

    single<HistoryRepository> {
        HistoryRepositoryImpl(get(), get())
    }
}