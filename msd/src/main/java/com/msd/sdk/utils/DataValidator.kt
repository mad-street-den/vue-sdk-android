package com.msd.sdk.utils

import android.content.Context
import com.msd.sdk.data.model.RecommendationRequest
import com.msd.sdk.helper.client.callbacks.RecommendationCallback
import org.json.JSONObject

object DataValidator {

    fun validateClientData(token: String?, context: Context?) {
        if (token.isNullOrEmpty())
            SDKLogger.logSDKInfo(
                LOG_INFO_TAG_GENERIC,
                INIT_SDK_TOKEN_ERROR
            )
        if (context == null)
            SDKLogger.logSDKInfo(
                LOG_INFO_TAG_GENERIC,
                INIT_SDK_CONTEXT_ERROR
            )
    }

    fun validateEventSanity(event: String?, properties: JSONObject? = null) {
        val missingDataKeys: ArrayList<String> = ArrayList()
        if (event.isNullOrEmpty())
            SDKLogger.logSDKInfo(
                LOG_INFO_TAG_EVENT_TRACKING,
                "An event was recorded without an event name"
            )
        for (objectKey in properties?.keys()!!) {
            if (properties.isNull(objectKey))
                missingDataKeys.add("$objectKey ")
        }
        if (missingDataKeys.isNotEmpty()) {
            var missingString = ""
            missingDataKeys.forEach { keys ->
                missingString += "$keys, "
            }
            missingString = missingString.substring(0, missingString.length - 2)
            SDKLogger.logSDKInfo(
                LOG_INFO_TAG_EVENT_TRACKING,
                "An event was recorded without the following mandatory event properties $missingString"
            )
        }
    }

    fun validateRecommendationSanity(
        properties: RecommendationRequest,
        callback: RecommendationCallback?
    ) {
        if (properties.catalogs == null)
            SDKLogger.logSDKInfo(
                LOG_INFO_TAG_RECOMMENDATION,
                "A request was made without the catalog data"
            )
        if (callback == null)
            SDKLogger.logSDKInfo(
                LOG_INFO_TAG_RECOMMENDATION,
                "Callback instance received by the sdk is empty"
            )
    }
}