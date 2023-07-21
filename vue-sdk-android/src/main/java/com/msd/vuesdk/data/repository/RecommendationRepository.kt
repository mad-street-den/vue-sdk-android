package com.msd.vuesdk.data.repository

import com.msd.vuesdk.data.networkcallbacks.NetworkCallback
import org.json.JSONObject

interface RecommendationRepository {
        suspend fun getRecommendation(
            request: JSONObject,
            networkCallback: NetworkCallback,
            token: String,
            correlationId: String?
        )

}