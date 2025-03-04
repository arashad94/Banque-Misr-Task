package com.banquemisr.pdpui.presentation.view

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import androidx.constraintlayout.compose.*
import androidx.constraintlayout.compose.layoutId
import com.banquemisr.designsystem.*
import com.banquemisr.designsystem.BMText.HeadingL
import com.banquemisr.designsystem.BMText.HeadingM
import com.banquemisr.designsystem.BMText.HeadingS
import com.banquemisr.pdpcomponent.domain.model.MovieDetails

@Composable
internal fun PdpContent(movie: MovieDetails) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth(),
            constraintSet = decoupleConstraints()
        ) {
            MovieImage(movie.posterPath)
            MovieTag(movie.status)
        }

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(8.dp))
        MovieTitle(movie.title)
        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
        MovieOverview(movie.overview)
        Spacer(modifier = Modifier.fillMaxWidth().height(8.dp))
        MovieGenres(movie.genres)
    }
}

@Composable
fun MovieTag(tag: String) {
    Box(modifier = Modifier.padding(8.dp).layoutId("tag")) {
        Text(
            text = tag.uppercase(),
            maxLines = 1,
            color = BMTheme.colors.textPrimary,
            modifier = Modifier
                .wrapContentWidth()
                .background(ColorPalette.grey300, shape = RoundedCornerShape(4.dp))
                .padding(horizontal = 8.dp, vertical = 6.dp)
        )
    }
}

@Composable
fun MovieImage(url: String) {
    BMImage(modifier = Modifier.layoutId("image"), imageUrl = url)
}

@Composable
fun MovieTitle(title: String) {
    HeadingL(text = title)
}

@Composable
fun MovieOverview(overview: String) {
    HeadingM(
        textModifier = BMTextModifier(color = BMTheme.colors.textSecondary),
        text = overview
    )
}

@Composable
fun MovieGenres(genres: List<String>) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(genres) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .background(ColorPalette.grey300, shape = RoundedCornerShape(4.dp))
                    .padding(8.dp)
            ) { HeadingS(text = it) }
        }
    }
}

private fun decoupleConstraints() = ConstraintSet {
    val image = createRefFor("image")
    val tag = createRefFor("tag")

    constrain(image) {
        top.linkTo(parent.top)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
        bottom.linkTo(parent.bottom)
    }

    constrain(tag) {
        start.linkTo(parent.start)
        bottom.linkTo(parent.bottom)
    }
}
