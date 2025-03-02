package com.banquemisr.homecomponent.data.mapper

import com.banquemisr.homecomponent.data.model.MoviesTypeDto
import com.banquemisr.homecomponent.domain.model.*

internal class DefaultMoviesByTypeMapper : MoviesByTypeMapper {
    override fun transform(moviesTypeDto: MoviesTypeDto): MoviesType {
        return MoviesType(
            results = moviesTypeDto.results.map {
                Movie(
                    adult = it.adult,
                    id = it.id,
                    title = it.title,
                    posterPath = it.posterPath,
                    overview = it.overview,
                    releaseDate = it.releaseDate
                )
            }
        )
    }
}
