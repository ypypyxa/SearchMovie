package com.practiCUM.searchmovie.data

import com.practiCUM.searchmovie.data.dto.NamesSearchResponse
import com.practiCUM.searchmovie.data.dto.NamesSearchRequest
import com.practiCUM.searchmovie.domain.api.NamesRepository
import com.practiCUM.searchmovie.domain.models.Person
import com.practiCUM.searchmovie.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NamesRepositoryImpl(private val networkClient: NetworkClient) : NamesRepository {

    override fun searchNames(expression: String): Flow<Resource<List<Person>>> = flow {
        val response = networkClient.doRequest(NamesSearchRequest(expression))
        when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }
            200 -> {
                with(response as NamesSearchResponse) {
                    val data = results.map {
                        Person(id = it.id,
                            name = it.title,
                            description = it.description,
                            photoUrl = it.image)
                    }
                    emit(Resource.Success(data))
                }
            }
            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }
}