package com.sample.golf.utils

import android.util.Log
import com.sample.golf.BuildConfig

fun log(message : String){
    if (BuildConfig.DEBUG) {
        Log.e("GOLF", "print: $message")
    }
}

