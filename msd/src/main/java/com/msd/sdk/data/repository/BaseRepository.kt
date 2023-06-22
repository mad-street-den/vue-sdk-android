package com.msd.sdk.data.repository

abstract class BaseRepository {
    protected var correlationId:String? = null
    protected val headers = if (!correlationId.isNullOrEmpty()) {
        mapOf("x-correlation-id" to correlationId)
    } else {
        emptyMap()
    }
}