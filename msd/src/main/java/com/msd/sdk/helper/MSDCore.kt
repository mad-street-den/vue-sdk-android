package com.msd.sdk.helper

import android.content.Context
import com.msd.sdk.data.model.RecommendationRequest
import com.msd.sdk.helper.client.MSDClient
import com.msd.sdk.helper.client.callbacks.RecommendationCallback
import com.msd.sdk.presenter.EventPresenter
import com.msd.sdk.presenter.RecommendationPresenter
import com.msd.sdk.utils.DataValidator
import com.msd.sdk.utils.SDKLogger
import org.json.JSONObject


internal class MSDCore(
    var token: String,
    var context: Context?,
    var baseURL: String,
    loggingEnabled: Boolean
) : MSDClient {

    private var userId: String? = null
    private var eventPresenter: EventPresenter
    private var recommendationPresenter: RecommendationPresenter

    init {
        DataValidator.validateClientData(token, context)
        SDKLogger.isLoggingEnabled = loggingEnabled
        eventPresenter = EventPresenter(context, token, baseURL)
        recommendationPresenter = RecommendationPresenter(context, token, baseURL)
    }

    override fun track(eventName: String, properties: JSONObject) {
        DataValidator.validateEventSanity(eventName, properties)
        context?.let { eventPresenter.trackEvent(eventName, properties) }
    }

    override fun getRecommendationsByPage(
        pageReference: String,
        properties: RecommendationRequest,
        callback: RecommendationCallback
    ) {
        getRecommendations(properties, callback)
    }

    override fun getRecommendationsByText(
        textReference: String,
        properties: RecommendationRequest,
        callback: RecommendationCallback
    ) {
        getRecommendations(properties, callback)
    }

    override fun getRecommendationsByStrategy(
        strategyReference: String,
        properties: RecommendationRequest,
        callback: RecommendationCallback
    ) {
        getRecommendations(properties, callback)

    }

    override fun getRecommendationsByModule(
        moduleReference: String,
        properties: RecommendationRequest,
        callback: RecommendationCallback
    ) {
        getRecommendations(properties, callback)
    }

    override fun setUserId(userId: String) {
        this.userId = userId
    }

    private fun getRecommendations(
        properties: RecommendationRequest,
        callback: RecommendationCallback
    ) {
        DataValidator.validateRecommendationSanity(properties, callback)
        context?.let { recommendationPresenter.getRecommendation(properties, callback) }
    }

}