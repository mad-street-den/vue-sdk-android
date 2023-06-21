package com.msd.sdk.helper.client.callbacks

import androidx.annotation.Keep
import com.msd.sdk.data.model.DiscoverEventsResponse
import org.json.JSONObject

@Keep
interface DiscoverEventsCallback {
    fun onDiscoverEventsFetched(response: DiscoverEventsResponse)
    fun onError(errorResponse: JSONObject)
}