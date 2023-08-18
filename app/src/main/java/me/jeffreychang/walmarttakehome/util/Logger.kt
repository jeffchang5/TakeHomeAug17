package me.jeffreychang.walmarttakehome.util

import android.util.Log

// Custom and extensible logger
object Logger {

    fun d(tag: String, message: String) {
        Log.d(tag, message)
    }

    fun e(tag: String, message: String) {
        Log.e(tag, message)
    }
}