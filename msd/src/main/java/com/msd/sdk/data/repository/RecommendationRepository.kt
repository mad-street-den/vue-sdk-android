package com.msd.sdk.data.repository

import com.msd.sdk.data.networkcallbacks.NetworkCallback
import org.json.JSONObject

interface RecommendationRepository {
        suspend fun getRecommendation(
            request: JSONObject,
            networkCallback: NetworkCallback,
            token: String,
            correlationId: String?
        )

}