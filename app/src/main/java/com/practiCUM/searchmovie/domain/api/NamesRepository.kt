package com.practiCUM.searchmovie.domain.api

import com.practiCUM.searchmovie.domain.models.Person
import com.practiCUM.searchmovie.util.Resource
import kotlinx.coroutines.flow.Flow

interface NamesRepository {

    fun searchNames(expression: String): Flow<Resource<List<Person>>>
}