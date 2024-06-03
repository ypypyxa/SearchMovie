package com.practiCUM.searchmovie.util

import android.app.Activity
import android.content.Context
import com.practiCUM.searchmovie.data.MoviesRepositoryImpl
import com.practiCUM.searchmovie.data.network.RetrofitNetworkClient
import com.practiCUM.searchmovie.domain.api.MoviesInteractor
import com.practiCUM.searchmovie.domain.api.MoviesRepository
import com.practiCUM.searchmovie.domain.impl.MoviesInteractorImpl
import com.practiCUM.searchmovie.presentation.movies.MoviesSearchPresenter
import com.practiCUM.searchmovie.presentation.poster.PosterPresenter
import com.practiCUM.searchmovie.presentation.movies.MoviesView
import com.practiCUM.searchmovie.presentation.poster.PosterView

object Creator {
    private fun getMoviesRepository(context: Context): MoviesRepository {
        return MoviesRepositoryImpl(RetrofitNetworkClient(context))
    }

    fun provideMoviesInteractor(context: Context): MoviesInteractor {
        return MoviesInteractorImpl(getMoviesRepository(context))
    }

    fun provideMoviesSearchPresenter(context: Context): MoviesSearchPresenter {
        return MoviesSearchPresenter(context = context)
    }

    fun providePosterPresenter(
        view: PosterView,
        url: String
    ): PosterPresenter {
        return PosterPresenter(view, url)
    }
}