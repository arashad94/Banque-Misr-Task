package com.banquemisr.homecomponent.data.model

import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
internal data class MoviesTypeDto(
    @field:Json(name = "results")
    val results: List<MovieDto>
)

@JsonClass(generateAdapter = true)
internal data class MovieDto(
    @field:Json(name = "adult")
    val adult: Boolean,
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "poster_path")
    val posterPath: String,
    @field:Json(name = "overview")
    val overview: String,
    @field:Json(name = "release_date")
    val releaseDate: String
)
