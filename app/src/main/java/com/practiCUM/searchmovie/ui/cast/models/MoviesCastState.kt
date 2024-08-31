package com.practiCUM.searchmovie.ui.cast.models

import com.practiCUM.searchmovie.core.ui.RVItem
import com.practiCUM.searchmovie.domain.models.MovieCast
import com.practiCUM.searchmovie.ui.cast.MoviesCastRVItem

sealed interface MoviesCastState {

    object Loading : MoviesCastState

    data class Content(
        val fullTitle: String,
        // Поменяли тип ячеек на более общий
        val items: List<RVItem>,
    ) : MoviesCastState

    data class Error(
        val message: String,
    ) : MoviesCastState

}