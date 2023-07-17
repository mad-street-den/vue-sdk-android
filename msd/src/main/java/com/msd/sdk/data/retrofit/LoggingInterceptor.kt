package com.msd.sdk.data.retrofit

import com.msd.sdk.utils.SDKLogger
import com.msd.sdk.utils.constants.LOG_INFO_TAG_GENERIC
import okhttp3.Interceptor
import okhttp3.Response

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestLog =
            "Request URL: ${request.url} " +
                    "Request Method: ${request.method} " +
                    "Request Headers: ${request.headers} " +
                    "Request Body: ${request.body}"


        val response = chain.proceed(request)

        val responseLog =
            "Response Code: ${response.code}" +
                    "Response Headers: ${response.headers}" +
                    "Response Body: ${response.body}"

        SDKLogger.logSDKInfo(LOG_INFO_TAG_GENERIC, requestLog + responseLog)

        return response
    }
}