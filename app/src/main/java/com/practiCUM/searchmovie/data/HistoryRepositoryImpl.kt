package com.practiCUM.searchmovie.data

import com.practiCUM.searchmovie.data.converters.MovieDbConvertor
import com.practiCUM.searchmovie.data.db.AppDatabase
import com.practiCUM.searchmovie.data.db.entity.MovieEntity
import com.practiCUM.searchmovie.domain.db.HistoryRepository
import com.practiCUM.searchmovie.domain.models.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HistoryRepositoryImpl(
    private val appDatabase: AppDatabase,
    private val movieDbConvertor: MovieDbConvertor,
) : HistoryRepository {
    override fun historyMovies(): Flow<List<Movie>> = flow {
        val movies = appDatabase.movieDao().getMovies()
        emit(convertFromMovieEntity(movies))
    }

    private fun convertFromMovieEntity(movies: List<MovieEntity>): List<Movie> {
        return movies.map { movie -> movieDbConvertor.map(movie) }
    }
}