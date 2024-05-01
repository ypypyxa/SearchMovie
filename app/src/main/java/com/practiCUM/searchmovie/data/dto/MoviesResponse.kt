package com.practiCUM.searchmovie.data.dto

class MoviesResponse(val searchType: String,
                     val expression: String,
                     val results: List<MovieDto>)  : Response()