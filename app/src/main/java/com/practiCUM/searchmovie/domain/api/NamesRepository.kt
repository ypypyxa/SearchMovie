package com.practiCUM.searchmovie.domain.api

import com.practiCUM.searchmovie.domain.models.Person
import com.practiCUM.searchmovie.util.Resource

interface NamesRepository {

    fun searchNames(expression: String): Resource<List<Person>>
}