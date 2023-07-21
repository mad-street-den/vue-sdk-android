package com.msd.vuesdk.constants

import com.msd.vuesdk.data.model.RecommendationRequest
import org.json.JSONObject

fun getRecommendationSuccessInput(catalogId: String): RecommendationRequest {
    val recommendationInput = JSONObject(
        """{
            "$catalogId": {
                "fields": [
                    "title",
                    "price",
                    "image_link",
                    "link"
                ],
                "context": {
                    "variant_id": "5789256482843"
                }
            }
        }""".trimIndent()
    )
    return RecommendationRequest(catalogs = recommendationInput)
}

fun getRecommendationFailInput(): RecommendationRequest {
    return RecommendationRequest(catalogs = JSONObject())
}