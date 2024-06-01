package com.practiCUM.searchmovie.presentation.movies

import com.practiCUM.searchmovie.domain.models.Movie
import com.practiCUM.searchmovie.ui.movies.models.MoviesState

interface MoviesView {
    // Методы, меняющие внешний вид экрана
    fun render(state: MoviesState)
    // Методы «одноразовых событий»
    fun showToast(additionalMessage: String)
}