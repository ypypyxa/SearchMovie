package com.practiCUM.searchmovie.data.dto

class MovieCastResponse(
    val actors: List<ActorResponse>,
    val directors: DirectorsResponse,
    val errorMessage: String,
    val fullTitle: String,
    val imDbId: String,
    val others: List<OtherResponse>,
    val title: String,
    val type: String,
    val writers: WritersResponse,
    val year: String
) : Response()

class ActorResponse(
    val asCharacter: String,
    val id: String,
    val image: String,
    val name: String
)

class DirectorsResponse(
    val items: List<CastItemResponse>,
    val job: String
)

class OtherResponse(
    val items: List<CastItemResponse>,
    val job: String
)

class WritersResponse(
    val items: List<CastItemResponse>,
    val job: String
)

class CastItemResponse(
    val description: String,
    val id: String,
    val name: String
)