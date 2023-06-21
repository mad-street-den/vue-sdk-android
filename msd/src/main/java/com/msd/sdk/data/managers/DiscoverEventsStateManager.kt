package com.msd.sdk.data.managers

import com.msd.sdk.data.model.DiscoverEventsResponse
import com.msd.sdk.data.networkcallbacks.NetworkCallback
import com.msd.sdk.data.repository.DiscoverEventsRepository
import com.msd.sdk.data.repository.DiscoverEventsRepositoryImplementation
import com.msd.sdk.data.state.DiscoverEventsState
import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONObject

class DiscoverEventsStateManager(baseUrl: String) {
    private val service: DiscoverEventsRepository = DiscoverEventsRepositoryImplementation(baseUrl)
    var discoverEventsState: MutableStateFlow<DiscoverEventsState?> = MutableStateFlow(null)

    suspend fun discoverEvents(token: String){
        service.discoverEvents(object: NetworkCallback {
            override suspend fun onResult(classObject: Any) {
                val discoverEventsResponse = classObject as DiscoverEventsResponse
                val discoverEventsStateResponse = DiscoverEventsState(discoverEventsResponse = discoverEventsResponse, errorResponse = null)
                this@DiscoverEventsStateManager.discoverEventsState.emit(discoverEventsStateResponse)
            }

            override suspend fun onError(errorObject: JSONObject) {
                val discoverEventsStateResponse = DiscoverEventsState(discoverEventsResponse = null, errorResponse = errorObject)
                this@DiscoverEventsStateManager.discoverEventsState.emit(discoverEventsStateResponse)
            }
        }, token)
    }
}