package com.msd.sdk.utils

import android.util.Log

object SDKLogger {
    var isLoggingEnabled = false

    fun logSDKInfo(tag:String,message:String)
    {
        if(isLoggingEnabled)
            Log.i(tag,message)
    }

}