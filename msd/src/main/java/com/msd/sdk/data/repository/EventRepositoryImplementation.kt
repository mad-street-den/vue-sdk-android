package com.msd.sdk.data.repository

import com.msd.sdk.data.networkcallbacks.NetworkCallback
import com.msd.sdk.data.retrofit.RetrofitClient
import com.msd.sdk.data.service.EventApiService
import org.json.JSONObject
import retrofit2.awaitResponse

class EventRepositoryImplementation : EventRepository {
    private var apisService: EventApiService? = null

    init {
        apisService = RetrofitClient.getRetrofitInstance()?.create(
            EventApiService::class.java
        )
    }

    override suspend fun trackEvent(
        eventName: String,
        properties: JSONObject,
        networkCallback: NetworkCallback
    ) {
        val call = apisService?.trackEvent(eventName, properties)
        val response = call?.awaitResponse()
        if (response?.isSuccessful == true) {
            response.body()?.let { networkCallback.onResult(it) }
        } else {
            response?.errorBody()?.let { networkCallback.onError(it) }
        }

    }


}