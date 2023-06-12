package com.msd.sdk.data.managers

import com.msd.sdk.data.networkcallbacks.NetworkCallback
import com.msd.sdk.data.repository.EventRepository
import com.msd.sdk.data.repository.EventRepositoryImplementation
import com.msd.sdk.data.state.EventState
import kotlinx.coroutines.flow.MutableStateFlow
import org.json.JSONObject

class EventStateManager(var baseURL: String) {
    private var service: EventRepository = EventRepositoryImplementation(baseURL)
    var eventState: MutableStateFlow<EventState?> = MutableStateFlow(null)


    suspend fun trackEvent( properties: JSONObject,token:String) {
        service.trackEvent(properties,object : NetworkCallback{
            override suspend fun onResult(classObject: Any) {
                val eventState = EventState(eventResponse = classObject as JSONObject, errorResponse = null)
                this@EventStateManager.eventState.emit(eventState)
            }

            override suspend fun onError(errorObject: JSONObject) {
                val eventState = EventState(eventResponse = null, errorResponse = errorObject)
                this@EventStateManager.eventState.emit(eventState)
            }

        },token)
    }

}