package com.msd.sdk.data.repository

import com.msd.sdk.data.networkcallbacks.NetworkCallback
import org.json.JSONObject

interface EventRepository {
    suspend fun trackEvent(properties: JSONObject,networkCallback: NetworkCallback,token:String)
}