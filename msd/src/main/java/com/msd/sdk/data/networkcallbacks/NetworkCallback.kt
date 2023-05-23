package com.msd.sdk.data.networkcallbacks

import java.util.*

interface NetworkCallback {
    fun onResult(classObject: Any)
    fun onError(classObject: Any)
}