package com.msd.vuesdk.helper

import android.content.Context
import com.msd.vuesdk.data.model.DiscoverEventsResponse
import com.msd.vuesdk.data.model.RecommendationRequest
import com.msd.vuesdk.helper.client.VueSDKClient
import com.msd.vuesdk.helper.client.callbacks.DiscoverEventsCallback
import com.msd.vuesdk.helper.client.callbacks.RecommendationCallback
import com.msd.vuesdk.helper.client.config.VueSDKConfig
import com.msd.vuesdk.presenter.DiscoverEventsPresenter
import com.msd.vuesdk.presenter.EventPresenter
import com.msd.vuesdk.presenter.RecommendationPresenter
import com.msd.vuesdk.utils.DataValidator
import com.msd.vuesdk.utils.DiscoverEventUtils
import com.msd.vuesdk.utils.PreferenceHelper
import com.msd.vuesdk.utils.SDKLogger
import com.msd.vuesdk.utils.constants.*
import org.json.JSONObject


internal class VueSDKCore(
    var token: String,
    var context: Context?,
    var baseURL: String,
    bloxUUID:String? = null
) : VueSDKClient {

    private var eventPresenter: EventPresenter
    private var recommendationPresenter: RecommendationPresenter
    private var discoverEventsPresenter: DiscoverEventsPresenter
    private var discoverEvents: DiscoverEventsResponse? = null

    private fun fetchDiscoverEvents() {
        discoverEventsPresenter.discoverEvents(object : DiscoverEventsCallback {
            override fun onDiscoverEventsFetched(response: DiscoverEventsResponse) {
                DiscoverEventUtils.getDiscoveryUtilInstance().fetchSuccess = true
                discoverEvents = response
            }
            override fun onError(errorResponse: JSONObject) {
                SDKLogger.logSDKInfo(
                    LOG_INFO_TAG_DISCOVER_EVENTS,
                    errorResponse.toString()
                )
            }
        })
    }
    init {
        DataValidator.validateClientData(token, context, baseURL)
        eventPresenter = EventPresenter(context, token, baseURL)
        recommendationPresenter = RecommendationPresenter(context, token, baseURL)
        discoverEventsPresenter = DiscoverEventsPresenter(context, token, baseURL)
        bloxUUID?.let {
            setBloxUUID(bloxUUID)
        }
        fetchDiscoverEvents()
    }

    override fun track(eventName: String, properties: JSONObject,correlationId:String?, sdkConfig: VueSDKConfig?) {
        if(!DiscoverEventUtils.getDiscoveryUtilInstance().fetchSuccess){
            fetchDiscoverEvents()
        }
        DataValidator.validateEventSanity(eventName, properties)
        context?.let { eventPresenter.trackEvent(eventName, properties,correlationId,sdkConfig) }
    }

    override fun getRecommendationsByPage(
        pageReference: String,
        properties: RecommendationRequest,
        callback: RecommendationCallback,correlationId:String?, sdkConfig: VueSDKConfig?
    ) {
        getRecommendations(properties, callback, PAGE_REF, pageReference,correlationId,sdkConfig)
    }

    override fun getRecommendationsByStrategy(
        strategyReference: String,
        properties: RecommendationRequest,
        callback: RecommendationCallback,correlationId:String?, sdkConfig: VueSDKConfig?
    ) {
        getRecommendations(properties, callback, STRATEGY_REF, strategyReference,correlationId,sdkConfig)

    }

    override fun getRecommendationsByModule(
        moduleReference: String,
        properties: RecommendationRequest,
        callback: RecommendationCallback,
        correlationId: String?,
        sdkConfig: VueSDKConfig?
    ) {
        getRecommendations(properties, callback, MODULE_REF, moduleReference,correlationId,sdkConfig)
    }

    override fun discoverEvents(callback: DiscoverEventsCallback) {
        if (discoverEvents === null) {
            context?.let { discoverEventsPresenter.discoverEvents(callback) }
        } else {
            callback.onDiscoverEventsFetched(discoverEvents!!)
        }
    }

    override fun setUserId(userId: String) {
        if (userId.isEmpty())
            SDKLogger.logSDKInfo(
                LOG_INFO_TAG_GENERIC,
                "ERROR: Code $USER_ID_EMPTY Message:$USER_ID_EMPTY_DESC"
            )
        context?.let {
            PreferenceHelper.setSharedPreferenceString(it, PreferenceHelper.USER_ID, userId)
        }
    }

    override fun resetUserProfile() {
        context?.let {
            PreferenceHelper.clearSpecificDataPref(it, PreferenceHelper.USER_ID)
        }
    }

    override fun setLogging(loggingState: Boolean) {
        SDKLogger.isLoggingEnabled = loggingState
    }

    override fun getBloxUUID(): String? {
        context?.let {
           val bloxUUID =  PreferenceHelper.getSharedPreferenceString(it, PreferenceHelper.MAD_UUID)
            if(bloxUUID.isBlank())
                return null
            else
                return bloxUUID
        }
        return null
    }

    override fun setBloxUUID(bloxUUid: String) {
        if (bloxUUid.isBlank())
            SDKLogger.logSDKInfo(
                LOG_INFO_TAG_GENERIC,
                "ERROR: Code $MAD_UUID_EMPTY Message:$MAD_UUID_EMPTY_DESC"
            )
        else
        {
            context?.let {
                PreferenceHelper.setSharedPreferenceString(it, PreferenceHelper.MAD_UUID, bloxUUid)
            }
        }

    }

    private fun getRecommendations(
        properties: RecommendationRequest,
        callback: RecommendationCallback,
        methodKey: String,
        methodValue: String,correlationId:String?,sdkConfig: VueSDKConfig?
    ) {
        DataValidator.validateRecommendationSanity(properties)
        context?.let {
            recommendationPresenter.getRecommendation(
                properties,
                callback,
                methodKey,
                methodValue,correlationId,sdkConfig
            )
        }
    }

}