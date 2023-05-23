package com.msd.sdk.data.repository

import com.msd.sdk.data.networkcallbacks.NetworkCallback
import com.msd.sdk.data.retrofit.RetrofitClient
import com.msd.sdk.data.service.EventApiService
import com.msd.sdk.data.service.RecommendationApiService
import org.json.JSONObject
import retrofit2.awaitResponse

class RecommendationRepositoryImplementation : RecommendationRepository {
    private var apisService: RecommendationApiService? = null

    init {
        apisService = RetrofitClient.getRetrofitInstance()?.create(
            RecommendationApiService::class.java
        )
    }

    override suspend fun getRecommendation(request: JSONObject, networkCallback: NetworkCallback) {
        val call = apisService?.getRecommendations(request)
        val response = call?.awaitResponse()
        if (response?.isSuccessful == true) {
            response.body()?.let { networkCallback.onResult(it) }
        } else {
            response?.errorBody()?.let { networkCallback.onError(it) }
        }
    }
}