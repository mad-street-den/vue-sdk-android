package com.msd.vuesdk.data.repository

import com.msd.vuesdk.data.networkcallbacks.NetworkCallback

interface DiscoverEventsRepository {
    suspend fun discoverEvents(networkCallback: NetworkCallback, token: String)
}