package com.msd.sdk.presenter

import android.content.Context
import org.json.JSONObject

class EventPresenter(private var context: Context?, token: String, baseURL: String): BasePresenter() {

    fun trackEvent(eventName: String, properties: JSONObject)
    {
        baseContext = context
        //Call API and FLOW
    }

}