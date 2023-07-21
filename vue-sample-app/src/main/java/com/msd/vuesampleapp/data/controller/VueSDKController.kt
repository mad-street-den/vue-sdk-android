package com.msd.vuesampleapp.data.controller

import android.content.Context
import com.msd.vuesdk.helper.client.VueSDK
import com.msd.vuesdk.helper.client.VueSDKClient


class VueSDKController {
    companion object {
        private var sdkClient: VueSDKClient? = null

        fun initVueSDK(context: Context, token: String, baseUrl: String) {
           sdkClient = VueSDK.getInstance(
                context, token, baseUrl
            )
           sdkClient?.setLogging(true)
        }
        fun getVueSDKInstance(): VueSDKClient? {
            return sdkClient!!
        }
    }
}