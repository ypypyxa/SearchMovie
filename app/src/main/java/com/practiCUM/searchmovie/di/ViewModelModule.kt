package com.practiCUM.searchmovie.di

import com.practiCUM.searchmovie.ui.movies.MoviesSearchViewModel
import com.practiCUM.searchmovie.ui.details.poster.PosterViewModel
import com.practiCUM.searchmovie.ui.details.about.AboutMovieViewModel
import com.practiCUM.searchmovie.ui.cast.MoviesCastViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MoviesSearchViewModel(get(), androidApplication())
    }

    viewModel { (url: String) ->
        PosterViewModel(url)
    }

    viewModel {(movieId: String) ->
        AboutMovieViewModel(movieId, get())
    }

    viewModel { (movieId: String) ->
        MoviesCastViewModel(movieId, get())
    }
}