package com.sample.golf.utils

import android.util.Log
import android.view.View
import com.sample.golf.BuildConfig

fun log(message : String){
    if (BuildConfig.DEBUG) {
        Log.e("GOLF", "print: $message")
    }
}

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.GONE
}