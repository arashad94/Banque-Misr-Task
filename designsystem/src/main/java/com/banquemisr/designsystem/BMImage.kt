package com.banquemisr.designsystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.banquemisr.designsystem.model.BMImageOptions
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p" // This also comes from API

@Composable
fun BMImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    imageOptions: BMImageOptions = BMImageOptions()
) {
    CoilImage(
        modifier = modifier,
        imageModel = { "$IMAGE_BASE_URL$imageUrl" },
        imageLoader = { BMImageLoader.getInstance() },
        imageOptions = ImageOptions(
            contentScale = imageOptions.contentScale,
            alignment = imageOptions.alignment
        ),
        loading = {
            Box(modifier = modifier.shimmerBackground())
        },
        failure = {
            Image(
                painter = painterResource(R.drawable.placeholder),
                contentDescription = null
            )
        },
        previewPlaceholder = painterResource(id = R.drawable.placeholder)
    )
}
