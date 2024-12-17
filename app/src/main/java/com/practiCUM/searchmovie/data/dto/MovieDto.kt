package com.practiCUM.searchmovie.data.dto

data class MovieDto(val id: String,
                    val resultType: String,
                    val image: String,
                    val title: String,
                    val description: String,
                    var inFavorite: Boolean
    )