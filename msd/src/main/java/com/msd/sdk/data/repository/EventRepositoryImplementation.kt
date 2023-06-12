package com.msd.sdk.data.repository

import com.msd.sdk.data.networkcallbacks.NetworkCallback
import com.msd.sdk.data.retrofit.RetrofitClient
import com.msd.sdk.data.service.EventApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.HttpException

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
            val jsonObject = response?.string()?.let { JSONObject(it) }
            if (jsonObject?.has("errors") != true) {
                jsonObject?.let { networkCallback.onResult(jsonObject) }
            } else {
                jsonObject.get("errors").let {
                    networkCallback.onError(
                        jsonObject
                    )
                }

            }
        } catch (e: HttpException) {
            networkCallback.onError( JSONObject(e.response()?.errorBody()?.string()?:""))
        }


    }


}