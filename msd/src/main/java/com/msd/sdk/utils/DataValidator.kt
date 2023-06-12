package com.msd.sdk.utils

import android.content.Context
import com.msd.sdk.data.model.RecommendationRequest
import com.msd.sdk.helper.client.callbacks.RecommendationCallback
import org.json.JSONObject

object DataValidator {

    fun validateClientData(token: String?, context: Context?,baseUrl: String?) {
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
        if (baseUrl.isNullOrEmpty())
            SDKLogger.logSDKInfo(
                LOG_INFO_TAG_GENERIC,
                INIT_SDK_BASE_URL_ERROR
            )
    }

    fun validateEventSanity(event: String?,pageName:String?, properties: JSONObject? = null) {
        val missingDataKeys: ArrayList<String> = ArrayList()
        if (event.isNullOrEmpty())
            SDKLogger.logSDKInfo(
                LOG_INFO_TAG_EVENT_TRACKING,
                "An event was recorded without an event name"
            )
        if (pageName.isNullOrEmpty())
            SDKLogger.logSDKInfo(
                LOG_INFO_TAG_EVENT_TRACKING,
                "An event was recorded without a page name"
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
        properties: RecommendationRequest
    ) {
        if (properties.catalogs.length()==0)
            SDKLogger.logSDKInfo(
                LOG_INFO_TAG_RECOMMENDATION,
                "A request was made without the catalog data"
            )
    }
}