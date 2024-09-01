package com.practiCUM.searchmovie.domain.api

import com.practiCUM.searchmovie.domain.models.Person

interface NamesInteractor {

    fun searchNames(expression: String, consumer: NamesConsumer)

    interface NamesConsumer {
        fun consume(foundNames: List<Person>?, errorMessage: String?)
    }
}