package com.msd.sdk.utils

import android.content.Context
import com.msd.sdk.data.model.RecommendationRequest
import com.msd.sdk.utils.constants.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


object DataValidator {

    fun validateClientData(token: String?, context: Context?,baseUrl: String?) {
        if (token.isNullOrEmpty())
            SDKLogger.logSDKInfo(
                LOG_INFO_TAG_GENERIC,
                "ERROR: Code $TOKEN_EMPTY Message:$TOKEN_EMPTY_DESC"
            )
        if (context == null)
            SDKLogger.logSDKInfo(
                LOG_INFO_TAG_GENERIC,
                INIT_SDK_CONTEXT_ERROR
            )
        if (baseUrl.isNullOrEmpty())
            SDKLogger.logSDKInfo(
                LOG_INFO_TAG_GENERIC,
                "ERROR: Code $INIT_SDK_BASE_ERROR Message:$INIT_SDK_BASE_ERROR"
            )
    }

    fun validateEventSanity(event: String?,pageName:String?, properties: JSONObject? = null) {
        val missingDataKeys: ArrayList<String> = ArrayList()
        if (event.isNullOrEmpty())
            SDKLogger.logSDKInfo(
                LOG_INFO_TAG_EVENT_TRACKING,
                "ERROR: Code $EVENT_NAME_EMPTY Message:$EVENT_NAME_EMPTY_DESC"
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
                "ERROR: Code $DATA_FOR_EVENT_EMPTY Message:$DATA_FOR_EVENT_EMPTY_DESC"

            )
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
                "ERROR: Code $DATA_FOR_RECOMMENDATION_EMPTY Message:$DATA_FOR_RECOMMENDATION_EMPTY_DESC"
            )
    }

    fun jsonValidator(jsonString: String,hostTag: String) : Boolean
    {
        try {
            JSONObject(jsonString)
        } catch (ex: JSONException) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                JSONArray(jsonString)
            } catch (ex1: JSONException) {
                SDKLogger.logSDKInfo(hostTag,                "ERROR: Code $PROPERTY_VALIDATION Message:$PROPERTY_VALIDATION_DESC"
                )
                return false
            }
        }
        return true
    }

}