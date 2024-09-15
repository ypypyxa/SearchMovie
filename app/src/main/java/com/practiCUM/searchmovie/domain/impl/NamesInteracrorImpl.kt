package com.practiCUM.searchmovie.domain.impl

import com.practiCUM.searchmovie.domain.api.NamesInteractor
import com.practiCUM.searchmovie.domain.api.NamesRepository
import com.practiCUM.searchmovie.domain.models.Person
import com.practiCUM.searchmovie.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.concurrent.Executors

class NamesInteracrorImpl(private val repository: NamesRepository) : NamesInteractor {

    override fun searchNames(expression: String): Flow<Pair<List<Person>?, String?>> {
        return repository.searchNames(expression).map { result ->
            when(result) {
                is Resource.Success -> { Pair(result.data, null) }
                is Resource.Error -> { Pair(null, result.message) }
            }
        }
    }
}