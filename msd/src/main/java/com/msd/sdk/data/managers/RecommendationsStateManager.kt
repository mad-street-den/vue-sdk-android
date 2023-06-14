package com.msd.sdk.data.managers

import com.msd.sdk.data.networkcallbacks.NetworkCallback
import com.msd.sdk.data.repository.RecommendationRepository
import com.msd.sdk.data.repository.RecommendationRepositoryImplementation
import com.msd.sdk.data.state.RecommendationState
import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONObject

class RecommendationsStateManager(baseURL: String) {
    private var service: RecommendationRepository = RecommendationRepositoryImplementation(baseURL)
    var recommendationState: MutableStateFlow<RecommendationState?> = MutableStateFlow(null)


    suspend fun getRecommendations(properties: JSONObject, token:String) {
        service.getRecommendation(properties,object : NetworkCallback {
            override suspend fun onResult(classObject: Any) {
               var classObjectJson = classObject as JSONObject
                var jsonArray = classObjectJson.getJSONArray("data")
                val eventState = RecommendationState(recommendationResponse = jsonArray , errorResponse = null)
                this@RecommendationsStateManager.recommendationState.emit(eventState)
            }

            override suspend fun onError(errorObject: JSONObject) {
                val eventState = RecommendationState(recommendationResponse = null, errorResponse = errorObject)
                this@RecommendationsStateManager.recommendationState.emit(eventState)
            }

        },token)
    }

}