package com.msd.sdk.data.repository

import com.msd.sdk.data.networkcallbacks.NetworkCallback
import com.msd.sdk.data.retrofit.RetrofitClient
import com.msd.sdk.data.service.RecommendationApiService
import com.msd.sdk.utils.DataValidator
import com.msd.sdk.utils.LOG_INFO_TAG_RECOMMENDATION
import com.msd.sdk.utils.constants.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.HttpException
import java.net.UnknownHostException

class RecommendationRepositoryImplementation( baseURL: String) : RecommendationRepository {
    private var apisService: RecommendationApiService? = null

    init {
        apisService = RetrofitClient.getRetrofitInstance(baseURL)?.create(
            RecommendationApiService::class.java
        )
    }

    override suspend fun getRecommendation(request: JSONObject, networkCallback: NetworkCallback,token:String) {

        try {

            val requestBody =
                request.toString().toRequestBody("application/json".toMediaTypeOrNull())
            val response = apisService?.getRecommendations(requestBody, token)
            val json = response?.string()

            if(DataValidator.jsonValidator(response?.string()?:"", LOG_INFO_TAG_RECOMMENDATION)) {

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
                networkCallback.onError(JSONObject().put("code", PROPERTY_VALIDATION).put("message",
                    PROPERTY_VALIDATION_DESC
                ))
            }
        } catch (e: HttpException) {
            if(e.code() == 408)
                networkCallback.onError(JSONObject().put("code", TIME_OUT).put("message",
                    TIME_OUT_DESC
                ))
            else
                networkCallback.onError( JSONObject(e.response()?.errorBody()?.string()?:""))

        }
        catch (e: UnknownHostException)
        {
            networkCallback.onError( JSONObject().put("code",UNKNOWN_ERROR).put("message",e.message))

        }

    }
}