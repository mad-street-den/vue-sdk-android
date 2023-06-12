package com.msd.sdk.presenter

import android.content.Context
import com.msd.sdk.utils.PreferenceHelper
import java.util.UUID

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

    protected fun getUserID(): String {
        baseContext?.let {
            val userId: String =
                PreferenceHelper.getSharedPreferenceString(it, PreferenceHelper.USER_ID);
            return userId;
        }
        return ""
    }

    abstract fun checkInternalErrors()


}