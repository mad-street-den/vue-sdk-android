package com.msd.vuesdk.helper.client.callbacks

import androidx.annotation.Keep
import com.msd.vuesdk.data.model.DiscoverEventsResponse
import org.json.JSONObject

@Keep
interface DiscoverEventsCallback {
    fun onDiscoverEventsFetched(response: DiscoverEventsResponse)
    fun onError(errorResponse: JSONObject)
}