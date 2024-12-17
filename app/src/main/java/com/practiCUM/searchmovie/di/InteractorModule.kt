package com.practiCUM.searchmovie.di

import com.practiCUM.searchmovie.domain.api.MoviesInteractor
import com.practiCUM.searchmovie.domain.api.NamesInteractor
import com.practiCUM.searchmovie.domain.db.HistoryInteractor
import com.practiCUM.searchmovie.domain.impl.HistoryInteractorImpl
import com.practiCUM.searchmovie.domain.impl.MoviesInteractorImpl
import com.practiCUM.searchmovie.domain.impl.NamesInteracrorImpl
import org.koin.dsl.module

val interactorModule = module {

    single<MoviesInteractor> {
        MoviesInteractorImpl(get())
    }

    single<NamesInteractor> {
        NamesInteracrorImpl(get())
    }

    single<HistoryInteractor> {
        HistoryInteractorImpl(get())
    }

}