package com.msd.vuesdk.data.retrofit

import com.msd.vuesdk.utils.SDKLogger
import com.msd.vuesdk.utils.constants.LOG_INFO_TAG_GENERIC
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
            "Request URL: ${request.url} \n" +
                    "Request Method: ${request.method} \n" +
                    "Request Headers: ${request.headers} \n" +
                    "Request Body: ${request.body?.let { requestBodyToString(it) }} \n"


        val response = chain.proceed(request)

        val responseLog =
            " \nResponse Code: ${response.code} \n" +
                    "Response Headers: ${response.headers} \n" +
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