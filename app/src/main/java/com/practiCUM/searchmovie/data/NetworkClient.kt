package com.practiCUM.searchmovie.data

import com.practiCUM.searchmovie.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response

}