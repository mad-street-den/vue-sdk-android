package com.msd.sdk.data.repository

import com.msd.sdk.data.networkcallbacks.NetworkCallback

interface DiscoverEventsRepository {
    suspend fun discoverEvents(networkCallback: NetworkCallback, token: String)
}