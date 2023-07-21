package com.msd.vuesdk.helper.client.callbacks

import androidx.annotation.Keep
import org.json.JSONArray
import org.json.JSONObject

@Keep
interface RecommendationCallback {

    fun onRecommendationsFetched(response: JSONArray)//add Model class
    fun onError(errorResponse: JSONObject)//add error model class

}