package com.msd.sdk.data.repository

import com.msd.sdk.data.networkcallbacks.NetworkCallback
import com.msd.sdk.data.retrofit.RetrofitClient
import com.msd.sdk.data.service.RecommendationApiService
import com.msd.sdk.utils.DataValidator
import com.msd.sdk.utils.constants.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.HttpException
import java.net.SocketTimeoutException

class RecommendationRepositoryImplementation( baseURL: String) : RecommendationRepository,BaseRepository() {
    private var apisService: RecommendationApiService? = null

    init {
        apisService = RetrofitClient.getRetrofitInstance(baseURL)?.create(
            RecommendationApiService::class.java
        )
    }

    override suspend fun getRecommendation(
        request: JSONObject,
        networkCallback: NetworkCallback,
        token: String,
        correlationId: String?
    ) {
        this.correlationId = correlationId
        setHeaders()

        try {

            val requestBody =
                request.toString().toRequestBody("application/json".toMediaTypeOrNull())
            val response = apisService?.getRecommendations(requestBody, token,headers!!)
            val json = response?.string()

            if(DataValidator.jsonValidator(json ?:"", LOG_INFO_TAG_RECOMMENDATION)) {

                val jsonObject = json?.let { JSONObject(it) }
                if (jsonObject?.has("errors") != true) {
                    jsonObject?.let { networkCallback.onResult(jsonObject) }
                } else {
                    jsonObject.get("errors").let {
                        networkCallback.onError(
                            jsonObject
                        )
                    }

                }
            }
            else
            {
                networkCallback.onError(JSONObject().put("code", UNABLE_TO_DECODE).put("message",
                    UNABLE_TO_DECODE_DESC
                ))
            }
        } catch (e: HttpException) {
            if(e.code() == 408)
                networkCallback.onError(JSONObject().put("code", TIME_OUT).put("message",
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
        catch (e: SocketTimeoutException)
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