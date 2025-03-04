package com.banquemisr.pdpcomponent.domain.model

data class MovieDetails(
    val id: Long,
    val title: String,
    val posterPath: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val status: String,
    val genres: List<String>
)
