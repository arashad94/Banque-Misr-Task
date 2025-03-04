package com.banquemisr.designsystem

import android.content.Context
import android.os.Build
import coil.ImageLoader
import coil.decode.*

object BMImageLoader {

    private lateinit var imageLoader: ImageLoader

    fun initialise(applicationContext: Context) {
        imageLoader = createImageLoader(applicationContext)
    }

    fun getInstance() = imageLoader

    private fun createImageLoader(context: Context): ImageLoader {
        return ImageLoader.Builder(context).components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }.build()
    }
}
