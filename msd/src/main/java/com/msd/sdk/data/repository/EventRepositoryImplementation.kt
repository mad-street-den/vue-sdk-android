package com.msd.sdk.data.repository

import com.msd.sdk.data.networkcallbacks.NetworkCallback
import com.msd.sdk.data.retrofit.RetrofitClient
import com.msd.sdk.data.service.EventApiService
import com.msd.sdk.utils.DataValidator
import com.msd.sdk.utils.LOG_INFO_TAG_EVENT_TRACKING
import com.msd.sdk.utils.constants.TIME_OUT
import com.msd.sdk.utils.constants.TIME_OUT_DESC
import com.msd.sdk.utils.constants.UNKNOWN_ERROR
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONObject
import retrofit2.HttpException
import java.net.UnknownHostException

class EventRepositoryImplementation(var baseURL: String) : EventRepository {
    private var apisService: EventApiService? = null

    init {
        apisService = RetrofitClient.getRetrofitInstance(baseURL)?.create(
            EventApiService::class.java
        )
    }

    override suspend fun trackEvent(
        properties: JSONObject,
        networkCallback: NetworkCallback, token: String
    ) {

        try {

            val requestBody =
                properties.toString().toRequestBody("application/json".toMediaTypeOrNull())
            val response = apisService?.trackEvent(requestBody, token)
            val json = response?.string()
            if (DataValidator.jsonValidator(
                    response?.string() ?: "",
                    LOG_INFO_TAG_EVENT_TRACKING
                )
            ) {
                val jsonObject = json?.let { JSONObject(it) }
                if (jsonObject?.has("errors") != true) {
                    jsonObject?.let { networkCallback.onResult(jsonObject) }
                } else {
                    jsonObject.get("errors").let {
                        networkCallback.onError(
                            jsonObject
                        )
                    }

                }
            }
        } catch (e: HttpException) {
            if (e.code() == 408)
                networkCallback.onError(
                    JSONObject().put("code", TIME_OUT).put(
                        "message",
                        TIME_OUT_DESC
                    )
                )
            else
                networkCallback.onError(JSONObject(e.response()?.errorBody()?.string() ?: ""))
        } catch (e: UnknownHostException) {
            networkCallback.onError(
                JSONObject().put("code", UNKNOWN_ERROR).put("message", e.message)
            )

        }


    }


}