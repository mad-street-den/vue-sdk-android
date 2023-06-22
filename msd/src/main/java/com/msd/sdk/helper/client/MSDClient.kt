package com.msd.sdk.helper.client

import androidx.annotation.Keep
import com.msd.sdk.data.model.RecommendationRequest
import com.msd.sdk.helper.client.callbacks.DiscoverEventsCallback
import com.msd.sdk.helper.client.callbacks.RecommendationCallback
import org.json.JSONObject

@Keep
interface MSDClient{
    fun track(eventName: String, properties: JSONObject,correlationId:String? = null)
    fun getRecommendationsByPage(pageReference:String,properties: RecommendationRequest, callback: RecommendationCallback,correlationId:String? = null)
    fun getRecommendationsByText(textReference:String,properties: RecommendationRequest, callback: RecommendationCallback,correlationId:String? = null)
    fun getRecommendationsByStrategy(strategyReference:String,properties: RecommendationRequest, callback: RecommendationCallback,correlationId:String? = null)
    fun getRecommendationsByModule(moduleReference:String,properties: RecommendationRequest, callback: RecommendationCallback,correlationId:String? = null)
    fun discoverEvents(callback: DiscoverEventsCallback)
    fun setUserId(userId:String)
    fun resetUserProfile()
    fun setLogging(loggingState: Boolean)

}
