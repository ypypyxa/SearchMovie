package com.practiCUM.searchmovie.domain.impl

import com.practiCUM.searchmovie.domain.api.NamesInteractor
import com.practiCUM.searchmovie.domain.api.NamesRepository
import com.practiCUM.searchmovie.util.Resource
import java.util.concurrent.Executors

class NamesInteracrorImpl(private val repository: NamesRepository) : NamesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun searchNames(expression: String, consumer: NamesInteractor.NamesConsumer) {
        executor.execute {
            when(val resource = repository.searchNames(expression)) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(resource.data, resource.message) }
            }
        }
    }
}