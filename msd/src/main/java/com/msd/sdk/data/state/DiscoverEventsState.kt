package com.msd.sdk.data.state

import com.msd.sdk.data.model.DiscoverEventsResponse
import org.json.JSONObject

class DiscoverEventsState(var errorResponse: JSONObject?, var discoverEventsResponse: DiscoverEventsResponse? = null){}
