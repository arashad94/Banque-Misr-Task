package com.banquemisr.homecomponent.domain.model

internal data class MoviesType(
    val results: List<Movie>
)

internal data class Movie(
    val adult: Boolean,
    val id: Long,
    val title: String,
    val posterPath: String,
    val overview: String,
    val releaseDate: String
)
