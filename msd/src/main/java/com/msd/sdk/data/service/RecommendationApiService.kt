package com.msd.sdk.data.service

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.POST
import java.util.*

interface RecommendationApiService {

    @POST("/api/v1/search")
    suspend fun getRecommendations(request: JSONObject): Call<JSONObject>

}