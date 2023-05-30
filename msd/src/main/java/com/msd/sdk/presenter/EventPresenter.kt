package com.msd.sdk.presenter

import android.content.Context
import org.json.JSONObject

class EventPresenter(private var context: Context?, token: String, baseURL: String,userId:String?): BasePresenter() {
    init {
        baseContext = context
        baseUserId = userId
    }
    fun trackEvent(eventName: String, properties: JSONObject)
    {
        //Call API and FLOW
    }

}