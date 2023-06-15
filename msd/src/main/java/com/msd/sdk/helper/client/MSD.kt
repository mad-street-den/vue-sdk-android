package com.msd.sdk.helper.client

import android.content.Context
import androidx.annotation.Keep
import com.msd.sdk.helper.MSDCore

@Keep
class MSD {

    @Keep
    companion object{
        @Keep
        @JvmStatic
        fun getInstance(context: Context,token:String,baseURL:String) : MSDClient
        {
            return MSDCore(context = context, token = token, baseURL = baseURL)
        }
    }

}