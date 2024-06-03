package com.practiCUM.searchmovie

import android.app.Application
import com.practiCUM.searchmovie.presentation.movies.MoviesSearchPresenter

class MoviesApplication : Application() {

    var moviesSearchPresenter : MoviesSearchPresenter? = null

}