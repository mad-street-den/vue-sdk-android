package com.msd.vuesdk.presenter

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.webkit.URLUtil
import com.msd.vuesdk.utils.PreferenceHelper
import com.msd.vuesdk.utils.SDKLogger
import com.msd.vuesdk.utils.constants.*
import java.util.*


abstract class BasePresenter {
    var baseContext: Context? = null
    var baseURLHolder: String? = ""

    protected fun getMadUUID(): String {

        baseContext?.let {
            var uuid: String? =
                PreferenceHelper.getSharedPreferenceString(it, PreferenceHelper.MAD_UUID)
            if (uuid.isNullOrEmpty()) {
                uuid = UUID.randomUUID().toString()
                PreferenceHelper.setSharedPreferenceString(it, PreferenceHelper.MAD_UUID, uuid)
            }
            return uuid ?: ""

        }
        return ""
    }

    protected fun getUserID(): String {
        baseContext?.let {
            val userId: String =
                PreferenceHelper.getSharedPreferenceString(it, PreferenceHelper.USER_ID)
            return userId
        }
        return ""
    }

    protected fun isNetworkAvailable(): Boolean {
        val connectivityManager =
            baseContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            return connectivityManager.activeNetworkInfo?.isConnected ?: false
        }
    }

    abstract fun isValidationPassed(): Boolean

    protected fun isBaseValidationPassed(): Boolean {
        var errorPassed = true
        if (!isNetworkAvailable()) {
            SDKLogger.logSDKInfo(
                LOG_INFO_TAG_EVENT_TRACKING, "ERROR: Code $NO_INTERNET Message:$NO_INTERNET_DESC"
            )
            errorPassed = false
        }
        if (!URLUtil.isValidUrl(baseURLHolder)) {
            SDKLogger.logSDKInfo(
                LOG_INFO_TAG_EVENT_TRACKING, "ERROR: Code $INVALID_URL Message:$INVALID_URL_DESC"
            )
            errorPassed = false
        }
        return errorPassed
    }

}