package com.msd.sdk.presenter

import android.content.Context
import android.webkit.URLUtil
import com.msd.sdk.data.managers.RecommendationsStateManager
import com.msd.sdk.data.model.RecommendationRequest
import com.msd.sdk.helper.client.callbacks.RecommendationCallback
import com.msd.sdk.utils.MSDUtils
import com.msd.sdk.utils.constants.DATA_FOR_RECOMMENDATION_EMPTY
import com.msd.sdk.utils.constants.DATA_FOR_RECOMMENDATION_EMPTY_DESC
import com.msd.sdk.utils.constants.INVALID_URL
import com.msd.sdk.utils.constants.INVALID_URL_DESC
import com.msd.sdk.utils.constants.NO_INTERNET
import com.msd.sdk.utils.constants.NO_INTERNET_DESC
import com.msd.sdk.utils.constants.PLATFORM_GENERIC_VALUE
import com.msd.sdk.utils.constants.PLATFORM_VALUE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class RecommendationPresenter(context: Context?, var token: String, private var baseURL: String) :
    BasePresenter() {
    private var recommendationsStateManager: RecommendationsStateManager
    private var injectedProperties: JSONObject? = null
    private var callbackErrorCode: String = ""
    private var callbackErrorDescription: String = ""
    private var properties: RecommendationRequest? = null

    init {
        baseContext = context
        baseURLHolder = baseURL
        recommendationsStateManager = RecommendationsStateManager(baseURL)
    }

    fun getRecommendation(
        properties: RecommendationRequest,
        callback: RecommendationCallback,
        methodKey: String,
        methodValue: String, correlationId: String?
    ) {
        this.properties = properties
        if (isValidationPassed()) {
            CoroutineScope(Dispatchers.IO).launch {
                injectedProperties = injectMandatoryData(methodKey, methodValue, properties)
                recommendationsStateManager.getRecommendations(
                    injectedProperties!!,
                    token,
                    correlationId
                )
                recommendationsStateManager.recommendationState.collect { it ->
                    if (it?.recommendationResponse != null) {
                        it.recommendationResponse?.let { response ->
                            callback.onRecommendationsFetched(response)

                        }
                    } else {
                        it?.errorResponse?.let { it1 -> callback.onError(it1) }
                    }
                }


            }
        } else {
            if (!isNetworkAvailable())
                callback.onError(
                    JSONObject().put("code", NO_INTERNET).put("message", NO_INTERNET_DESC)
                )
            else if (!URLUtil.isValidUrl(baseURL))
                callback.onError(
                    JSONObject().put("code", INVALID_URL).put("message", INVALID_URL_DESC)
                )
            else
                callback.onError(
                    JSONObject().put("code", callbackErrorCode)
                        .put("message", callbackErrorDescription)
                )
        }
    }

    private fun injectMandatoryData(
        methodKey: String,
        methodValue: String?,
        properties: RecommendationRequest
    ): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put("blox_uuid", getMadUUID())
        jsonObject.put("catalogs", properties.catalogs)
        jsonObject.put("config", properties.config)
        jsonObject.put("explain", properties.explain)
        jsonObject.put("max_bundles", properties.max_bundles)
        jsonObject.put("max_content", properties.max_content)
        jsonObject.put("min_bundles", properties.min_bundles)
        jsonObject.put("min_content", properties.min_content)
        jsonObject.put("page_num", properties.page_num)
        jsonObject.put("referrer", PLATFORM_VALUE)
        jsonObject.put("skip_cache", properties.skip_cache)
        jsonObject.put("unbundle", properties.unbundle)
        jsonObject.put("url", baseContext?.applicationContext?.packageName)
        jsonObject.put("medium", PLATFORM_VALUE)
        jsonObject.put("platform", PLATFORM_GENERIC_VALUE)
        if (getUserID().isNotEmpty())
            jsonObject.put("user_id", getUserID())
        jsonObject.put("timestamp", System.currentTimeMillis())
        jsonObject.put(methodKey, methodValue)
        return MSDUtils.eliminateNullFromJson(jsonObject)
    }

    override fun isValidationPassed(): Boolean {
        var errorPassed = true
        if (properties?.catalogs?.length() == 0) {
            callbackErrorCode = DATA_FOR_RECOMMENDATION_EMPTY
            callbackErrorDescription = DATA_FOR_RECOMMENDATION_EMPTY_DESC
            errorPassed = false

        }
        return super.isBaseValidationPassed() && errorPassed
    }

}