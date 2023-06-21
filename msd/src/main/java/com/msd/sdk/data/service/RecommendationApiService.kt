package com.msd.sdk.data.service

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface RecommendationApiService {

    @POST("search")
    suspend fun getRecommendations(@Body properties: RequestBody, @Header("x-api-key")token:String ): ResponseBody

}