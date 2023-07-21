package com.msd.vuesdk.data.repository

import com.msd.vuesdk.data.networkcallbacks.NetworkCallback
import org.json.JSONObject

interface EventRepository {
    suspend fun trackEvent(
        properties: JSONObject,
        networkCallback: NetworkCallback,
        token: String,
        correlationId: String?
    )
}