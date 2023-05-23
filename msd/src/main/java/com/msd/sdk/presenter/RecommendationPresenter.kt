package com.msd.sdk.presenter

import android.content.Context
import com.msd.sdk.helper.client.callbacks.RecommendationCallback
import org.json.JSONObject

class RecommendationPresenter(private var context: Context): BasePresenter() {
    fun getRecommendation(properties: JSONObject,callback: RecommendationCallback)
    {
        baseContext = context
        //Call API and FLOW
    }
}