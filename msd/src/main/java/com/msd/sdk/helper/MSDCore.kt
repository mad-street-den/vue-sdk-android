package com.msd.sdk.helper

import android.content.Context
import com.msd.sdk.data.model.DiscoverEventsResponse
import com.msd.sdk.data.model.RecommendationRequest
import com.msd.sdk.helper.client.MSDClient
import com.msd.sdk.helper.client.callbacks.DiscoverEventsCallback
import com.msd.sdk.helper.client.callbacks.RecommendationCallback
import com.msd.sdk.presenter.DiscoverEventsPresenter
import com.msd.sdk.presenter.EventPresenter
import com.msd.sdk.presenter.RecommendationPresenter
import com.msd.sdk.utils.DataValidator
import com.msd.sdk.utils.DiscoverEventUtils
import com.msd.sdk.utils.PreferenceHelper
import com.msd.sdk.utils.SDKLogger
import com.msd.sdk.utils.constants.*
import org.json.JSONObject


internal class MSDCore(
    var token: String,
    var context: Context?,
    var baseURL: String,
) : MSDClient {

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
        fetchDiscoverEvents()
    }

    override fun track(eventName: String, properties: JSONObject) {
        if(!DiscoverEventUtils.getDiscoveryUtilInstance().fetchSuccess){
            fetchDiscoverEvents()
        }
        DataValidator.validateEventSanity(eventName, properties)
        context?.let { eventPresenter.trackEvent(eventName, properties) }
    }

    override fun getRecommendationsByPage(
        pageReference: String,
        properties: RecommendationRequest,
        callback: RecommendationCallback
    ) {
        getRecommendations(properties, callback, PAGE_REF, pageReference)
    }

    override fun getRecommendationsByText(
        textReference: String,
        properties: RecommendationRequest,
        callback: RecommendationCallback
    ) {
        getRecommendations(properties, callback, PAGE_REF, textReference)
    }

    override fun getRecommendationsByStrategy(
        strategyReference: String,
        properties: RecommendationRequest,
        callback: RecommendationCallback,
    ) {
        getRecommendations(properties, callback, STRATEGY_REF, strategyReference)

    }

    override fun getRecommendationsByModule(
        moduleReference: String,
        properties: RecommendationRequest,
        callback: RecommendationCallback
    ) {
        getRecommendations(properties, callback, MODULE_REF, moduleReference)
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

    private fun getRecommendations(
        properties: RecommendationRequest,
        callback: RecommendationCallback,
        methodKey: String,
        methodValue: String
    ) {
        DataValidator.validateRecommendationSanity(properties)
        context?.let {
            recommendationPresenter.getRecommendation(
                properties,
                callback,
                methodKey,
                methodValue
            )
        }
    }

}