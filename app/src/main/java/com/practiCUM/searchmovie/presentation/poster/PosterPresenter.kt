package com.practiCUM.searchmovie.presentation.poster

class PosterPresenter(
    private val view: PosterView,
    private val url: String
) {

    fun onCreate() {
        view.setupPoster(url)
    }
}