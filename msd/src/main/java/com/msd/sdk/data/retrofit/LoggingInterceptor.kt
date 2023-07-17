package com.msd.sdk.data.retrofit

import com.msd.sdk.utils.SDKLogger
import com.msd.sdk.utils.constants.LOG_INFO_TAG_GENERIC
import okhttp3.Interceptor
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import okio.BufferedSource

class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestLog =
            "Request URL: ${request.url} " +
                    "Request Method: ${request.method} " +
                    "Request Headers: ${request.headers} " +
                    "Request Body: ${request.body?.let { requestBodyToString(it) }}"


        val response = chain.proceed(request)

        val responseLog =
            "Response Code: ${response.code}" +
                    "Response Headers: ${response.headers}" +
                    "Response Body: ${response.body?.let { responseBodyToString(it) }}"

        SDKLogger.logSDKInfo(LOG_INFO_TAG_GENERIC, requestLog + responseLog)

        return response
    }

    private fun requestBodyToString(requestBody: RequestBody): String {
        val buffer = Buffer()
        requestBody.writeTo(buffer)
        return buffer.readUtf8()
    }

    private fun responseBodyToString(responseBody: ResponseBody): String {
        val source: BufferedSource = responseBody.source()
        source.request(Long.MAX_VALUE) // Ensure the entire response body is read

        val buffer: Buffer = source.buffer
        return  buffer.clone().readUtf8()
    }
}