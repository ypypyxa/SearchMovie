package com.practiCUM.searchmovie.data

import com.practiCUM.searchmovie.data.dto.NamesSearchResponse
import com.practiCUM.searchmovie.data.dto.NamesSearchRequest
import com.practiCUM.searchmovie.domain.api.NamesRepository
import com.practiCUM.searchmovie.domain.models.Person
import com.practiCUM.searchmovie.util.Resource

class NamesRepositoryImpl(private val networkClient: NetworkClient) : NamesRepository {

    override fun searchNames(expression: String): Resource<List<Person>> {
        val response = networkClient.doRequest(NamesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                with(response as NamesSearchResponse) {
                    Resource.Success(results.map {
                        Person(id = it.id,
                            name = it.title,
                            description = it.description,
                            photoUrl = it.image)
                    })
                }
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}