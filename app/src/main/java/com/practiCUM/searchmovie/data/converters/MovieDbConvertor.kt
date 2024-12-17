package com.practiCUM.searchmovie.data.converters

import com.practiCUM.searchmovie.data.db.entity.MovieEntity
import com.practiCUM.searchmovie.data.dto.MovieDto
import com.practiCUM.searchmovie.domain.models.Movie

class MovieDbConvertor {

    fun map(movie: MovieDto): MovieEntity {
        return MovieEntity(movie.id, movie.resultType, movie.image, movie.title, movie.description, movie.inFavorite)
    }

    fun map(movie: MovieEntity): Movie {
        return Movie(movie.id, movie.resultType, movie.image, movie.title, movie.description, movie.inFavorite)
    }
}