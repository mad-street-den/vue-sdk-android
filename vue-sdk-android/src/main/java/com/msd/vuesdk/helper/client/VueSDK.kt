package com.msd.vuesdk.helper.client

import android.content.Context
import androidx.annotation.Keep
import com.msd.vuesdk.helper.VueSDKCore

@Keep
class VueSDK {

    @Keep
    companion object{
        @Keep
        @JvmStatic
        fun getInstance(context: Context,token:String,baseURL:String) : VueSDKClient
        {
            return VueSDKCore(context = context, token = token, baseURL = baseURL)
        }
    }

}