package com.msd.sdk.helper

import android.content.Context
import com.msd.sdk.data.model.RecommendationRequest
import com.msd.sdk.helper.client.MSDClient
import com.msd.sdk.helper.client.callbacks.RecommendationCallback
import com.msd.sdk.presenter.EventPresenter
import com.msd.sdk.presenter.RecommendationPresenter
import com.msd.sdk.utils.*
import com.msd.sdk.utils.DataValidator
import com.msd.sdk.utils.PreferenceHelper
import com.msd.sdk.utils.SDKLogger
import com.msd.sdk.utils.constants.DATA_FOR_RECOMMENDATION_EMPTY
import com.msd.sdk.utils.constants.DATA_FOR_RECOMMENDATION_EMPTY_DESC
import com.msd.sdk.utils.constants.USER_ID_EMPTY
import com.msd.sdk.utils.constants.USER_ID_EMPTY_DESC
import org.json.JSONObject


internal class MSDCore(
    var token: String,
    var context: Context?,
    var baseURL: String,
) : MSDClient {

    private var eventPresenter: EventPresenter
    private var recommendationPresenter: RecommendationPresenter

    init {
        DataValidator.validateClientData(token, context,baseURL)
        eventPresenter = EventPresenter(context, token, baseURL)
        recommendationPresenter = RecommendationPresenter(context, token, baseURL)
    }

    override fun track(eventName: String, properties: JSONObject, pageName: String?) {
        DataValidator.validateEventSanity(eventName, pageName, properties)
        context?.let { eventPresenter.trackEvent(eventName, properties,pageName) }
    }

    override fun getRecommendationsByPage(
        pageReference: String,
        properties: RecommendationRequest,
        callback: RecommendationCallback
    ) {
        getRecommendations(properties, callback, PAGE_REF,pageReference)
    }

    override fun getRecommendationsByText(
        textReference: String,
        properties: RecommendationRequest,
        callback: RecommendationCallback
    ) {
        getRecommendations(properties, callback, PAGE_REF,textReference)
    }

    override fun getRecommendationsByStrategy(
        strategyReference: String,
        properties: RecommendationRequest,
        callback: RecommendationCallback,
    ) {
        getRecommendations(properties, callback, STRATEGY_REF,strategyReference)

    }

    override fun getRecommendationsByModule(
        moduleReference: String,
        properties: RecommendationRequest,
        callback: RecommendationCallback
    ) {
        getRecommendations(properties, callback, MODULE_REF,moduleReference)
    }

    override fun setUserId(userId: String) {
        if(userId.isEmpty())
            SDKLogger.logSDKInfo(LOG_INFO_TAG_GENERIC,                "ERROR: Code $USER_ID_EMPTY Message:$USER_ID_EMPTY_DESC"
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
        methodKey:String,
        methodValue:String
    ) {
        DataValidator.validateRecommendationSanity(properties)
        context?.let { recommendationPresenter.getRecommendation(properties, callback,methodKey,methodValue) }
    }

}