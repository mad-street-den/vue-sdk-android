package com.msd.vuesdk.helper.client

import androidx.annotation.Keep
import com.msd.vuesdk.data.model.RecommendationRequest
import com.msd.vuesdk.helper.client.callbacks.DiscoverEventsCallback
import com.msd.vuesdk.helper.client.callbacks.RecommendationCallback
import com.msd.vuesdk.helper.client.config.VueSDKConfig
import org.json.JSONObject

@Keep
interface VueSDKClient{
    fun track(eventName: String, properties: JSONObject,correlationId:String? = null, sdkConfig: VueSDKConfig? = null)
    fun getRecommendationsByPage(pageReference:String,properties: RecommendationRequest, callback: RecommendationCallback,correlationId:String? = null, sdkConfig: VueSDKConfig? = null)
    fun getRecommendationsByStrategy(strategyReference:String,properties: RecommendationRequest, callback: RecommendationCallback,correlationId:String? = null, sdkConfig: VueSDKConfig? = null)
    fun getRecommendationsByModule(moduleReference:String,properties: RecommendationRequest, callback: RecommendationCallback,correlationId:String? = null, sdkConfig: VueSDKConfig? = null)
    fun discoverEvents(callback: DiscoverEventsCallback)
    fun setUserId(userId:String)
    fun resetUserProfile()
    fun setLogging(loggingState: Boolean)
    fun getBloxUUID(): String?
    fun setBloxUUID(bloxUUid: String)

}
