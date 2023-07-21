package com.msd.vuesdk

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.msd.vuesdk.helper.client.VueSDK
import com.msd.vuesdk.helper.client.VueSDKClient
import org.junit.AfterClass
import org.junit.Assert
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseInstrumentedTest {
    private val token = "<test sdk token>"
    private val baseUrl = "<test base url>"
    private val appContext: Context = InstrumentationRegistry.getInstrumentation().context

    companion object {
        var vueSDK: VueSDKClient? = null

        @AfterClass
        @JvmStatic
        fun teardownTests() {
            vueSDK = null;
        }
    }
    init {
        vueSDK = VueSDK.getInstance(appContext, token, baseUrl)
        Assert.assertNotNull(vueSDK)
    }
}