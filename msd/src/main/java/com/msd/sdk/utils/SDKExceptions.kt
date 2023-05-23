package com.msd.sdk.utils

class SDKInitException(
    override val message: String? = "",
    val isNetworkError: Boolean = false,
    val code: Int = 0,
    val status: Int = 0,
) :
    Exception(message)