package com.msd.sdk.helper.client.callbacks

import org.json.JSONObject

interface RecommendationCallback {

    fun onRecommendationsFetched(response: JSONObject)//add Model class
    fun onError(errorResponse: JSONObject)//add error model class

}