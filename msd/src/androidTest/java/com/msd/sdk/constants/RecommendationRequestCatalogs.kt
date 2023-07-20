package com.msd.sdk.constants

import com.msd.sdk.data.model.RecommendationRequest
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
                    "variant_id": "39596296700022"
                }
            }
        }""".trimIndent()
    )
    return RecommendationRequest(catalogs = recommendationInput)
}

fun getRecommendationFailInput(): RecommendationRequest {
    return RecommendationRequest(catalogs = JSONObject())
}