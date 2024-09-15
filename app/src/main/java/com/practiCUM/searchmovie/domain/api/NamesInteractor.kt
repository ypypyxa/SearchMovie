package com.practiCUM.searchmovie.domain.api

import com.practiCUM.searchmovie.domain.models.Person
import kotlinx.coroutines.flow.Flow

interface NamesInteractor {

    fun searchNames(expression: String): Flow<Pair<List<Person>?, String?>>
}