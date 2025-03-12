package com.sample.golf

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GolfApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }

}