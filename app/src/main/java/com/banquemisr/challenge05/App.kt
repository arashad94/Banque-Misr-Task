package com.banquemisr.challenge05

import android.app.Application
import com.banquemisr.designsystem.BMImageLoader
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        BMImageLoader.initialise(this)
        // FailFast.initDefault(errorLogger, !BuildConfig.DEBUG)
        // other initializations needed are also here
    }
}
