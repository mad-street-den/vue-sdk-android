package com.msd.sdk.utils

import android.util.Log

object SDKLogger {
    var isLoggingEnabled = true

    fun logSDKInfo(tag:String,message:String)
    {
        if(isLoggingEnabled)
            Log.i(tag,message)
    }

}