package com.banquemisr.pdpcomponent.data.mapper

import com.banquemisr.pdpcomponent.data.model.MovieDetailsDto
import com.banquemisr.pdpcomponent.domain.model.MovieDetails

internal fun interface MovieDetailsMapper {
    fun transform(movieDetailsDto: MovieDetailsDto): MovieDetails
}
