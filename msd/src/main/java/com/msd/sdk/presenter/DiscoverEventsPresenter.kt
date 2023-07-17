package com.msd.sdk.presenter

import android.content.Context
import com.msd.sdk.data.managers.DiscoverEventsStateManager
import com.msd.sdk.data.model.DiscoverEventsData
import com.msd.sdk.helper.client.callbacks.DiscoverEventsCallback
import com.msd.sdk.utils.DiscoverEventUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DiscoverEventsPresenter(context: Context?, var token: String, baseURL: String) :
    BasePresenter() {
    private var discoverEventsStateManager: DiscoverEventsStateManager? = null

    init {
        baseContext = context
        baseURLHolder = baseURL
        if (isBaseValidationPassed())
            discoverEventsStateManager = DiscoverEventsStateManager(baseURL)
    }

    private fun mapEventNamesToSourceFields(responseData: List<DiscoverEventsData>) {
        for (eventData in responseData) {
            val eventNameMap = HashMap<String, String>()
            eventData.events_schema?.let {
                for (eventSchema in eventData.events_schema) {
                    eventSchema.meta?.let { meta ->
                        eventSchema.source_field?.let { source ->
                            eventNameMap[meta] = source

                        }
                    }
                }
            }
            eventData.event_name?.let { name ->
                DiscoverEventUtils.getDiscoveryUtilInstance().discoverEventsLookup[name] =
                    eventNameMap
            }
        }

    }

    fun discoverEvents(callback: DiscoverEventsCallback) {
        if (isValidationPassed()) {
            CoroutineScope(Dispatchers.IO).launch {
                discoverEventsStateManager?.discoverEvents(token)
                discoverEventsStateManager?.discoverEventsState?.collect { it ->
                    if (it?.discoverEventsResponse != null) {
                        it.discoverEventsResponse?.let { response ->
                            callback.onDiscoverEventsFetched(response)
                            response.data?.events?.let {
                                mapEventNamesToSourceFields(response.data.events)
                            }
                        }
                    } else {
                        it?.errorResponse?.let { error -> callback.onError(error) }
                    }
                }
            }
        }
    }

    override fun isValidationPassed(): Boolean {
        return super.isBaseValidationPassed()
    }
}