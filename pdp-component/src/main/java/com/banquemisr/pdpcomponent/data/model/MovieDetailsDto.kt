package com.banquemisr.pdpcomponent.data.model

import com.squareup.moshi.*

@JsonClass(generateAdapter = true)
internal data class MovieDetailsDto(
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "poster_path")
    val posterPath: String,
    @field:Json(name = "overview")
    val overview: String,
    @field:Json(name = "release_date")
    val releaseDate: String,
    @field:Json(name = "vote_average")
    val voteAverage: Double,
    @field:Json(name = "status")
    val status: String,
    @field:Json(name = "genres")
    val genres: List<MovieGenre>
)

@JsonClass(generateAdapter = true)
internal data class MovieGenre(
    @field:Json(name = "id")
    val id: Long,
    @field:Json(name = "name")
    val name: String
)
