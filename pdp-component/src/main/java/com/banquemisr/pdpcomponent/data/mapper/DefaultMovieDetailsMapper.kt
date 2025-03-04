package com.banquemisr.pdpcomponent.data.mapper

import com.banquemisr.pdpcomponent.data.model.MovieDetailsDto
import com.banquemisr.pdpcomponent.domain.model.MovieDetails

internal class DefaultMovieDetailsMapper : MovieDetailsMapper {
    override fun transform(movieDetailsDto: MovieDetailsDto): MovieDetails {
        return MovieDetails(
            id = movieDetailsDto.id,
            title = movieDetailsDto.title,
            posterPath = movieDetailsDto.posterPath,
            overview = movieDetailsDto.overview,
            releaseDate = movieDetailsDto.releaseDate,
            voteAverage = movieDetailsDto.voteAverage,
            status = movieDetailsDto.status,
            genres = movieDetailsDto.genres.map { it.name }
        )
    }
}
