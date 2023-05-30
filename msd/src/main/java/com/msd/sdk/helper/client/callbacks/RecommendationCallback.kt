package com.msd.sdk.helper.client.callbacks

import com.msd.sdk.data.model.ErrorResponse
import org.json.JSONObject

interface RecommendationCallback {

    fun onRecommendationsFetched(response: JSONObject)//add Model class
    fun onError(errorResponse: ErrorResponse)//add error model class

}