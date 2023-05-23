package com.msd.sdk.helper.client

import androidx.annotation.Keep
import com.msd.sdk.helper.client.callbacks.RecommendationCallback
import org.json.JSONObject

@Keep
interface MSDClient{
    fun track(eventName: String, properties: JSONObject)
    fun getRecommendations(properties: JSONObject, callback: RecommendationCallback)
}
