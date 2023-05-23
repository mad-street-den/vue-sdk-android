package com.msd.sdk.data.service

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.POST
import java.util.*

interface EventApiService {

    @POST("/api/v1/events/track")
    suspend fun trackEvent(eventName:String,properties: JSONObject) : Call<JSONObject>

}