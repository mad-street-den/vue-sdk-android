package com.msd.sdk.presenter

import android.content.Context
import android.util.Log
import com.msd.sdk.data.managers.EventStateManager
import com.msd.sdk.utils.*
import com.msd.sdk.utils.constants.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class EventPresenter(private var context: Context?, var token: String, var baseURL: String) :
    BasePresenter() {
    private var eventStateManager: EventStateManager
    private var injectedProperties: JSONObject? = null
    private var properties: JSONObject? = null

    init {
        baseContext = context
        baseURLHolder = baseURL
        eventStateManager = EventStateManager(baseURL)

    }

    fun trackEvent(eventName: String, properties: JSONObject, pageName: String?) {
        this.properties = properties
        if (isValidationPassed()) {
            CoroutineScope(Dispatchers.IO).launch {
                injectedProperties = injectMandatoryData(eventName, properties, pageName)
                eventStateManager.trackEvent(injectedProperties!!, token)
                eventStateManager.eventState.collect { it ->
                    if (it?.eventResponse != null) {
                        Log.d(LOG_INFO_TAG_GENERIC, it.eventResponse.toString())
                    } else {
                        SDKLogger.logSDKInfo(
                            LOG_INFO_TAG_EVENT_TRACKING,
                            it?.errorResponse.toString()
                        )
                    }
                }


            }
        }
    }

    private fun injectMandatoryData(
        eventName: String,
        properties: JSONObject,
        pageName: String?
    ): JSONObject {
        properties.put("event_name", eventName)
        properties.put("blox_uuid", getMadUUID())
        properties.put("medium", MEDIUM_VALUE)
        properties.put("platform", PLATFORM_VALUE)
        properties.put("url", context?.applicationContext?.packageName)
        properties.put("page_name", pageName)
        properties.put("timestamp",System.currentTimeMillis().toInt())
        if (getUserID().isNotEmpty())
            properties.put("user_id", getUserID())
        return properties
    }

    override fun isValidationPassed(): Boolean {
        var errorPassed = true
        if (properties?.length()==0)
        {

            errorPassed = false
            SDKLogger.logSDKInfo(LOG_INFO_TAG_EVENT_TRACKING,                "ERROR: Code $DATA_FOR_EVENT_EMPTY Message:$DATA_FOR_EVENT_EMPTY_DESC"
            )

        }
        return super.isBaseValidationPassed() && errorPassed
    }



}