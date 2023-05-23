package com.msd.sdk.utils

import android.content.Context
import com.msd.sdk.helper.client.callbacks.RecommendationCallback
import org.json.JSONObject
import kotlin.collections.ArrayList

object DataValidator {

    fun validateClientData(token: String?, context: Context?) {
        if (token.isNullOrEmpty())
            throw SDKInitException(message = INIT_SDK_TOKEN_EXCEPTION)
        if (context == null)
            throw SDKInitException(message = INIT_SDK_CONTEXT_EXCEPTION)
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

    fun validateRecommendationSanity(properties: JSONObject, callback: RecommendationCallback) {
        val missingDataKeys: ArrayList<String> = ArrayList()
        for (objectKey in properties.keys()) {
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
                LOG_INFO_TAG_RECOMMENDATION,
                "A recommendation request was made without the following data $missingString"
            )
        }
    }

}