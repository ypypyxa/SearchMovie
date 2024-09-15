package com.practiCUM.searchmovie.data

import com.practiCUM.searchmovie.data.dto.Response

interface NetworkClient {
    suspend fun doRequest(dto: Any): Response
}