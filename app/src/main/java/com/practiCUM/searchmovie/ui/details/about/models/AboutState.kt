package com.practiCUM.searchmovie.ui.details.about.models

import com.practiCUM.searchmovie.domain.models.MovieDetails

sealed interface AboutState {

    data class Content(
        val movie: MovieDetails
    ) : AboutState

    data class Error(
        val message: String
    ) : AboutState

}