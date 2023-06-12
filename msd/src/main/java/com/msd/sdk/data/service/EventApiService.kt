package com.msd.sdk.data.service

import com.google.gson.JsonElement
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.*

interface EventApiService {

    @POST("events/track")
    suspend fun trackEvent(@Body properties: RequestBody, @Header("x-api-key")token:String ) : ResponseBody

}