package com.banquemisr.homecomponent.domain.model

data class MoviesType(
    val results: List<Movie>
)

data class Movie(
    val adult: Boolean,
    val id: Long,
    val title: String,
    val posterPath: String,
    val overview: String,
    val releaseDate: String
)
