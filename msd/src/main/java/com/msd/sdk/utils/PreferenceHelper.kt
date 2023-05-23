package com.msd.sdk.utils

import android.content.Context

object PreferenceHelper {
    private const val PREF_FILE_MSD = "MSD_DATA"

    const val MAD_UUID = "mad_uuid"


    internal fun setSharedPreferenceString(context: Context, key: String, value: String) {
        val settings =
            context.getSharedPreferences(PREF_FILE_MSD, 0)
        val editor = settings.edit()
        editor.putString(key, value)
        editor.apply()
    }

    internal fun getSharedPreferenceString(
        context: Context,
        key: String,
    ): String {
        val settings =
            context.getSharedPreferences(PREF_FILE_MSD, 0)

        return settings.getString(key,"")?:""
    }

    internal fun clearAllDataPref(context: Context)
    {
        val settings = context.getSharedPreferences(PREF_FILE_MSD,0)
        val editor = settings.edit()
        editor.clear()
        editor.apply()
    }

    internal fun clearSpecificDataPref(context: Context,key:String)
    {
        val settings = context.getSharedPreferences(PREF_FILE_MSD,0)
        val editor = settings.edit()
        editor.remove(key)
        editor.apply()
    }



}