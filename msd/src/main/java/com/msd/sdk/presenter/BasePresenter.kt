package com.msd.sdk.presenter

import android.content.Context
import com.msd.sdk.utils.PreferenceHelper
import java.util.*

abstract class BasePresenter {
    var baseContext: Context? = null
    var baseUserId: String? = null

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


}