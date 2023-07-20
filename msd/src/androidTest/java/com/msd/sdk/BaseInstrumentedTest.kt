package com.msd.sdk

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.msd.sdk.helper.client.MSD
import com.msd.sdk.helper.client.MSDClient
import org.junit.AfterClass
import org.junit.Assert
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
abstract class BaseInstrumentedTest {
    private val token = "<vue sdk token>"
    private val baseUrl = "<base url>"
    private val appContext: Context = InstrumentationRegistry.getInstrumentation().context

    companion object {
        var msd: MSDClient? = null

        @AfterClass
        @JvmStatic
        fun teardownTests() {
            msd = null;
        }
    }
    init {
        msd = MSD.getInstance(appContext, token, baseUrl)
        Assert.assertNotNull(msd)
    }
}