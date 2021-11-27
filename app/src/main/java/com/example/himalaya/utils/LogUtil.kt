package com.example.himalaya.utils

import android.util.Log

object LogUtil {

    private var isRelease=false

    @JvmStatic
    fun v(tag: String, msg: String) {
        if (!isRelease) {
            Log.v(tag, msg)
        }
    }

    fun d(tag: String, msg: String) {
        if (!isRelease) {
            Log.d(tag, msg)
        }
    }

    fun i(tag: String, msg: String) {
        if (!isRelease) {
            Log.i(tag, msg)
        }
    }

    fun w(tag: String, msg: String) {
        if (!isRelease) {
            Log.w(tag, msg)
        }

    }
    fun e(tag: String, msg: String) {
        if (!isRelease) {
            Log.e(tag, msg)
        }
    }

}