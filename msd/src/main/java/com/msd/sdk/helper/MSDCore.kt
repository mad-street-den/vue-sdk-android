package com.msd.sdk.helper

import android.content.Context
import androidx.annotation.Keep
import com.msd.sdk.helper.client.MSDClient
import com.msd.sdk.helper.client.callbacks.RecommendationCallback
import com.msd.sdk.presenter.EventPresenter
import com.msd.sdk.presenter.RecommendationPresenter
import com.msd.sdk.utils.DataValidator
import org.json.JSONObject


internal class MSDCore (var token: String?, var context: Context?) : MSDClient {

    init {
        DataValidator.validateClientData(token, context)
    }

    override fun track(eventName: String, properties: JSONObject) {
        DataValidator.validateEventSanity(eventName, properties)
        context?.let { EventPresenter(it).trackEvent(eventName, properties) }
    }

    override fun getRecommendations(properties: JSONObject, callback: RecommendationCallback) {
        DataValidator.validateRecommendationSanity(properties, callback)
        context?.let { RecommendationPresenter(it).getRecommendation(properties, callback) }
    }

}