package com.practiCUM.searchmovie.domain.db

import com.practiCUM.searchmovie.domain.models.Movie
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {

    fun historyMovies(): Flow<List<Movie>>
}