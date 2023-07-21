package com.msd.vuesdk.data.state

import com.msd.vuesdk.data.model.DiscoverEventsResponse
import org.json.JSONObject

class DiscoverEventsState(var errorResponse: JSONObject?, var discoverEventsResponse: DiscoverEventsResponse? = null){}
