package com.msd.sdk

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.msd.sdk.utils.PreferenceHelper
import com.msd.sdk.utils.SDKLogger
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MSDInstrumentedTestCore : BaseInstrumentedTest() {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.msd.sdk", appContext.packageName)
    }

    @Test
    fun testSetUserId() {
        val testUserId = "test_user_id"
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        msd?.setUserId(testUserId)
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
        msd?.setUserId(testUserId)
        assertEquals(
            PreferenceHelper.getSharedPreferenceString(
                appContext,
                PreferenceHelper.USER_ID
            ), testUserId
        )
        msd?.resetUserProfile()
        assertEquals(
            PreferenceHelper.getSharedPreferenceString(
                appContext,
                PreferenceHelper.USER_ID
            ), ""
        )
    }

    @Test
    fun testSetLogging() {
        msd?.setLogging(true)
        assertEquals(SDKLogger.isLoggingEnabled, true)
        msd?.setLogging(false)
        assertEquals(SDKLogger.isLoggingEnabled, false)
    }
}