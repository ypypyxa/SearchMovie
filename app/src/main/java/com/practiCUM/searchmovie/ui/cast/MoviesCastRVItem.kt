package com.practiCUM.searchmovie.ui.cast

import com.practiCUM.searchmovie.core.ui.RVItem
import com.practiCUM.searchmovie.domain.models.MovieCastPerson

sealed interface MoviesCastRVItem : RVItem {

    data class HeaderItem(
        val headerText: String,
    ) : MoviesCastRVItem

    data class PersonItem(
        val data: MovieCastPerson,
    ) : MoviesCastRVItem

}