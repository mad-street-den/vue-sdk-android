package com.msd.sdk.data.model

import org.json.JSONObject

data class RecommendationResponse(
    val correlation_id: Any?,
    val data: List<JSONObject>?,
    val message: String?,
    val request_id: String?,
    val status: String?,
    val errors: List<Error?>? = null
)

data class Error(
    val code: Int?,
    val message: String?
)