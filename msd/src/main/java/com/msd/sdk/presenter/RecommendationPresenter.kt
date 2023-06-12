package com.msd.sdk.presenter

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.msd.sdk.data.managers.RecommendationsStateManager
import com.msd.sdk.data.model.RecommendationRequest
import com.msd.sdk.helper.client.callbacks.RecommendationCallback
import com.msd.sdk.utils.MEDIUM_VALUE
import com.msd.sdk.utils.PLATFORM_GENERIC_VALUE
import com.msd.sdk.utils.PLATFORM_VALUE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject

class RecommendationPresenter(private var context: Context?, token: String, baseURL: String): BasePresenter() {
    private var recommendationsStateManager: RecommendationsStateManager
    private var injectedProperties: JSONObject? = null

    init {
        baseContext = context
        recommendationsStateManager = RecommendationsStateManager(baseURL)
    }

    fun getRecommendation(
        properties: RecommendationRequest,
        callback: RecommendationCallback,
        methodKey: String,
        methodValue: String
    ) {

    }

    private fun injectMandatoryData(methodKey: String, methodValue: String?,properties: RecommendationRequest): JSONObject
    {
        val json = Gson().toJson(properties)
        val jsonObject = JSONObject(json)
        jsonObject.put("blox_uuid",getMadUUID())
        jsonObject.put("platform", PLATFORM_GENERIC_VALUE)
        if(getUserID().isNotEmpty())
            jsonObject.put("user_id",getUserID())
        jsonObject.put(methodKey,methodValue)
        return jsonObject
    }

    override fun checkInternalErrors() {
        TODO("Not yet implemented")
    }
}