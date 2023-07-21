package com.msd.vuesdk

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.msd.vuesdk.utils.PreferenceHelper
import com.msd.vuesdk.utils.SDKLogger
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class VueSDKInstrumentedTestCore : BaseInstrumentedTest() {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.msd.vuesdk.test", appContext.packageName)
    }

    @Test
    fun testSetUserId() {
        val testUserId = "test_user_id"
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        vueSDK?.setUserId(testUserId)
        assertEquals(
            PreferenceHelper.getSharedPreferenceString(
                appContext,
                PreferenceHelper.USER_ID
            ), testUserId
        )
    }

    @Test
    fun testResetUserId() {
        val testUserId = "test_user_id"
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        vueSDK?.setUserId(testUserId)
        assertEquals(
            PreferenceHelper.getSharedPreferenceString(
                appContext,
                PreferenceHelper.USER_ID
            ), testUserId
        )
        vueSDK?.resetUserProfile()
        assertEquals(
            PreferenceHelper.getSharedPreferenceString(
                appContext,
                PreferenceHelper.USER_ID
            ), ""
        )
    }

    @Test
    fun testSetLogging() {
        vueSDK?.setLogging(true)
        assertEquals(SDKLogger.isLoggingEnabled, true)
        vueSDK?.setLogging(false)
        assertEquals(SDKLogger.isLoggingEnabled, false)
    }
}