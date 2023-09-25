package com.msd.vuesdk.presenter

import android.content.Context
import com.msd.vuesdk.data.managers.EventStateManager
import com.msd.vuesdk.helper.client.config.VueSDKConfig
import com.msd.vuesdk.utils.DiscoverEventUtils
import com.msd.vuesdk.utils.SDKLogger
import com.msd.vuesdk.utils.constants.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class EventPresenter(private var context: Context?, var token: String, baseURL: String) :
    BasePresenter() {
    private var eventStateManager: EventStateManager? = null
    private var injectedProperties: JSONObject? = null
    private var properties: JSONObject? = null

    init {
        baseContext = context
        baseURLHolder = baseURL
        if (isBaseValidationPassed())
            eventStateManager = EventStateManager(baseURL)
    }

    fun trackEvent(
        eventName: String,
        properties: JSONObject,
        correlationId: String?,
        sdkConfig: VueSDKConfig?
    ) {
        this.properties = properties
        if (isValidationPassed()) {
            CoroutineScope(Dispatchers.IO).launch {
                injectedProperties = injectMandatoryData(eventName, properties,sdkConfig)
                eventStateManager?.trackEvent(injectedProperties!!, token, correlationId)
                eventStateManager?.eventState?.collect {
                    if (it?.eventResponse != null) {
                        SDKLogger.logSDKInfo(LOG_INFO_TAG_EVENT_TRACKING, EVENT_SUCCESS_MESSAGE)
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
        sdkConfig: VueSDKConfig?,

        ): JSONObject {
        sdkConfig?.let {userConfig ->

            properties.put("medium", userConfig.medium?:MEDIUM_VALUE)
            properties.put("platform", userConfig.platform?:PLATFORM_VALUE)
            properties.put("url", userConfig.url?:baseContext?.applicationContext?.packageName)
            properties.put("referrer", userConfig.referrer?:PLATFORM_VALUE)

        }
        DiscoverEventUtils.getDiscoveryUtilInstance().discoverEventsLookup[eventName].let {
            properties.put(it?.get("event_name") ?: "event_name", eventName)
            properties.put(it?.get("blox_uuid") ?: "blox_uuid", getMadUUID())
            if (getUserID().isNotEmpty())
                properties.put(it?.get("user_id") ?: "user_id", getUserID())
        }
        return properties
    }

    override fun isValidationPassed(): Boolean {
        var errorPassed = true
        if (properties?.length() == 0) {
            errorPassed = false
            SDKLogger.logSDKInfo(
                LOG_INFO_TAG_EVENT_TRACKING,
                "ERROR: Code $DATA_FOR_EVENT_EMPTY Message:$DATA_FOR_EVENT_EMPTY_DESC"
            )

        }
        return super.isBaseValidationPassed() && errorPassed
    }


}