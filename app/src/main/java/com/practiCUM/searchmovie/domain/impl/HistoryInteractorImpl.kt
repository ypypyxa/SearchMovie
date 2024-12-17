package com.practiCUM.searchmovie.domain.impl

import com.practiCUM.searchmovie.domain.db.HistoryInteractor
import com.practiCUM.searchmovie.domain.db.HistoryRepository
import com.practiCUM.searchmovie.domain.models.Movie
import kotlinx.coroutines.flow.Flow

class HistoryInteractorImpl(private val historyRepository: HistoryRepository) : HistoryInteractor {
    override fun historyMovies(): Flow<List<Movie>> {
        return historyRepository.historyMovies()
    }
}