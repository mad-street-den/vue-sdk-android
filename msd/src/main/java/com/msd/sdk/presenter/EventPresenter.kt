package com.msd.sdk.presenter

import android.content.Context
import android.util.Log
import com.msd.sdk.data.managers.EventStateManager
import com.msd.sdk.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class EventPresenter(private var context: Context?, var token: String, baseURL: String) :
    BasePresenter() {
    private var eventStateManager: EventStateManager
    private var injectedProperties: JSONObject? = null

    init {
        baseContext = context
        eventStateManager = EventStateManager(baseURL)

    }

    fun trackEvent(eventName: String, properties: JSONObject, pageName: String?) {
        injectedProperties = injectMandatoryData(eventName, properties, pageName)
        CoroutineScope(Dispatchers.IO).launch {
            eventStateManager.trackEvent(injectedProperties!!, token)
            eventStateManager.eventState.collect { it ->
                if (it?.eventResponse != null) {
                    Log.d(LOG_INFO_TAG_GENERIC, it.eventResponse.toString())
                } else {
                    SDKLogger.logSDKInfo(LOG_INFO_TAG_EVENT_TRACKING, it?.errorResponse.toString())
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
        if (getUserID().isNotEmpty())
            properties.put("user_id", getUserID())
        return properties
    }

    override fun checkInternalErrors() {
        TODO("Not yet implemented")
    }

}