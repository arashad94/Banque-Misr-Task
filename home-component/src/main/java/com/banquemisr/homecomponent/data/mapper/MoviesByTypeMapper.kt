package com.banquemisr.homecomponent.data.mapper

import com.banquemisr.homecomponent.data.model.MoviesTypeDto
import com.banquemisr.homecomponent.domain.model.MoviesType

internal fun interface MoviesByTypeMapper {
    fun transform(moviesTypeDto: MoviesTypeDto): MoviesType
}
