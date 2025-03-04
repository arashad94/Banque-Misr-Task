package com.banquemisr.homeui.presentation.view

import BMTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.banquemisr.designsystem.*
import com.banquemisr.designsystem.BMText.HeadingL
import com.banquemisr.designsystem.BMText.HeadingM
import com.banquemisr.homecomponent.domain.model.Movie

@Composable
internal fun PlpScreen(movies: List<Movie>, onMovieClicked: (String) -> Unit) {
    LazyRow(modifier = Modifier.fillMaxSize()) {
        items(movies) { MovieItem(it, onMovieClicked) }
    }
}

@Composable
fun MovieItem(movie: Movie, onMovieClicked: (String) -> Unit) {
    Column(
        modifier = Modifier
            .width(180.dp)
            .padding(8.dp)
            .clickable { onMovieClicked(movie.id.toString()) }
    ) {
        BMImage(
            modifier = Modifier.width(160.dp).heightIn(min = 200.dp),
            imageUrl = movie.posterPath
        ) // For some reason loading the images from their side is not working
        MovieTitle(movie.title)
        MovieOverview(movie.overview)
    }
}

@Composable
fun MovieTitle(title: String) {
    HeadingL(text = title)
}

@Composable
fun MovieOverview(overview: String) {
    HeadingM(
        textModifier = BMTextModifier(
            maxLines = 3,
            color = BMTheme.colors.textSecondary,
            overflow = TextOverflow.Ellipsis
        ),
        text = overview
    )
}
