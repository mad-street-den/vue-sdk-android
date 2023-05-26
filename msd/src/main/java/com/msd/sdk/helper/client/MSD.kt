package com.msd.sdk.helper.client

import android.content.Context
import androidx.annotation.Keep
import com.msd.sdk.helper.MSDCore

@Keep
class MSD {

    @Keep
    companion object{
        fun getInstance(context: Context,token:String,baseURL:String,loggingEnabled:Boolean = false) : MSDClient
        {
            return MSDCore(context = context, token = token, baseURL = baseURL, loggingEnabled = loggingEnabled)
        }
    }

}