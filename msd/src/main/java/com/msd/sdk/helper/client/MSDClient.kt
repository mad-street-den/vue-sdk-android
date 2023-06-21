package com.msd.sdk.helper.client

import androidx.annotation.Keep
import com.msd.sdk.data.model.RecommendationRequest
import com.msd.sdk.helper.client.callbacks.DiscoverEventsCallback
import com.msd.sdk.helper.client.callbacks.RecommendationCallback
import org.json.JSONObject

@Keep
interface MSDClient{
    fun track(eventName: String, properties: JSONObject)
    fun getRecommendationsByPage(pageReference:String,properties: RecommendationRequest, callback: RecommendationCallback)
    fun getRecommendationsByText(textReference:String,properties: RecommendationRequest, callback: RecommendationCallback)
    fun getRecommendationsByStrategy(strategyReference:String,properties: RecommendationRequest, callback: RecommendationCallback)
    fun getRecommendationsByModule(moduleReference:String,properties: RecommendationRequest, callback: RecommendationCallback)
    fun discoverEvents(callback: DiscoverEventsCallback)
    fun setUserId(userId:String)
    fun resetUserProfile()
    fun setLogging(loggingState: Boolean)

}
