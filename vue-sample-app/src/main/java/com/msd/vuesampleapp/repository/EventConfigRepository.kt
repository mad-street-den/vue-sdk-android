package com.msd.vuesampleapp.repository

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import com.msd.vuesdk.data.model.DiscoverEventsResponse
import com.msd.vuesdk.helper.client.callbacks.DiscoverEventsCallback
import org.json.JSONObject

class EventConfigRepository() {
    private val msdClient = com.msd.vuesampleapp.data.controller.VueSDKController.getVueSDKInstance()
    var eventState = mutableStateOf<DiscoverEventsResponse?>(null)

    fun getConfigData() {
        msdClient?.discoverEvents(object : DiscoverEventsCallback {
            override fun onDiscoverEventsFetched(response: DiscoverEventsResponse) {
                eventState.value = response
            }

            override fun onError(errorResponse: JSONObject) {
                Log.d("Get config error", errorResponse.toString())
            }
        })
    }
}