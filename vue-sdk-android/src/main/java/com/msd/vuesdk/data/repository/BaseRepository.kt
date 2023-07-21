package com.msd.vuesdk.data.repository

abstract class BaseRepository {
    protected var correlationId:String? = null
    protected  var headers : Map<String, String>? = null
    fun setHeaders()
    {
        headers = if (!correlationId.isNullOrBlank()) {
            mapOf("x-correlation-id" to correlationId!!)
        } else {
            emptyMap()
        }
    }
}