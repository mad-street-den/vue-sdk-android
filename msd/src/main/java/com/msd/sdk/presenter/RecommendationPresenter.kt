package com.msd.sdk.presenter

import android.content.Context
import com.msd.sdk.data.model.RecommendationRequest
import com.msd.sdk.helper.client.callbacks.RecommendationCallback
import org.json.JSONObject

class RecommendationPresenter(private var context: Context?, token: String, baseURL: String): BasePresenter() {
    init {
        baseContext = context
    }
    fun getRecommendation(properties: RecommendationRequest,callback: RecommendationCallback)
    {

        //Call API and FLOW
    }
}