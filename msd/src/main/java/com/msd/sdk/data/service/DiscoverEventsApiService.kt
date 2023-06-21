package com.msd.sdk.data.service

import com.msd.sdk.data.model.DiscoverEventsResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface DiscoverEventsApiService {
    @GET("search/configs/metadata-pages")
    suspend fun discoverEvents(
        @Query("platform") platform: String,
        @Header("x-api-key") token: String
    ): DiscoverEventsResponse
}