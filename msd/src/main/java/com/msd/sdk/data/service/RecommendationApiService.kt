package com.msd.sdk.data.service

import com.msd.sdk.data.model.RecommendationResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.POST
import java.util.*

interface RecommendationApiService {

    @POST("search")
    suspend fun getRecommendations(request: JSONObject): Call<RecommendationResponse>

}