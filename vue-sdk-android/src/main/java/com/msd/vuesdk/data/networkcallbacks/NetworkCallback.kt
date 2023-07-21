package com.msd.vuesdk.data.networkcallbacks

import org.json.JSONObject

interface NetworkCallback {
   suspend fun onResult(classObject: Any)
  suspend  fun onError(errorObject: JSONObject)
}