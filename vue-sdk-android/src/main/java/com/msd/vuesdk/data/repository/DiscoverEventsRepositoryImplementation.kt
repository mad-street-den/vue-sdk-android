package com.msd.vuesdk.data.repository

import com.msd.vuesdk.data.networkcallbacks.NetworkCallback
import com.msd.vuesdk.data.retrofit.RetrofitClient
import com.msd.vuesdk.data.service.DiscoverEventsApiService
import com.msd.vuesdk.utils.constants.*
import org.json.JSONObject
import retrofit2.HttpException
import java.net.SocketTimeoutException

class DiscoverEventsRepositoryImplementation(baseUrl: String): DiscoverEventsRepository,BaseRepository() {
    private var apisService: DiscoverEventsApiService? = null

    init {
        apisService = RetrofitClient.getRetrofitInstance(baseUrl)?.create(
            DiscoverEventsApiService::class.java
        )
    }
    override suspend fun discoverEvents(networkCallback: NetworkCallback, token: String) {
        try {
            val response = apisService?.discoverEvents(platform = "android",token)
            networkCallback.onResult(response as Any)
        } catch (e: HttpException) {
            if(e.code() == 408)
                networkCallback.onError(
                    JSONObject().put("code", TIME_OUT).put("message",
                    TIME_OUT_DESC
                ))
            else if(e.code() in 500..599)
                networkCallback.onError(
                    JSONObject().put("code", SERVER_UNAVAILABLE).put(
                        "message",
                        SERVER_UNAVAILABLE_DESC
                    )
                )
            else
                networkCallback.onError( JSONObject(e.response()?.errorBody()?.string()?:""))
        }
        catch (e:SocketTimeoutException)
        {
            networkCallback.onError(
                JSONObject().put("code", TIME_OUT).put("message",
                    TIME_OUT_DESC
                ))
        }
        catch(e: Exception)
        {
            networkCallback.onError( JSONObject().put("code", UNKNOWN_ERROR).put("message",e.message))
        }
    }
}