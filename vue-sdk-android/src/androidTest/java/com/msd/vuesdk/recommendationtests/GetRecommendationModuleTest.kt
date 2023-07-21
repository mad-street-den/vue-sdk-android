package com.msd.vuesdk.recommendationtests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.msd.vuesdk.constants.getRecommendationFailInput
import com.msd.vuesdk.constants.getRecommendationSuccessInput
import com.msd.vuesdk.BaseInstrumentedTest
import com.msd.vuesdk.helper.client.callbacks.RecommendationCallback
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class GetRecommendationModuleTest : BaseInstrumentedTest() {
    private val moduleReference = "Similar Products - June30"

    @Test
    fun `testRecommendationsByModule-Get_recommendations_by_module_success`() {
        val signal = CountDownLatch(1)
        vueSDK?.getRecommendationsByModule(moduleReference,
            getRecommendationSuccessInput("3089e3d3ba"),
            callback = object : RecommendationCallback {
                override fun onRecommendationsFetched(response: JSONArray) {
                    assertTrue("Get recommendations response is null", response.length() > 0)
                    signal.countDown()
                }

                override fun onError(errorResponse: JSONObject) {
                    fail("Failed to get recommendation by Module")
                }
            })
        signal.await(3, TimeUnit.SECONDS)
    }

    @Test
    fun `testRecommendationsByStrategy-Get_recommendations_by_module_failure`() {
        val signal = CountDownLatch(1)

        vueSDK?.getRecommendationsByModule(moduleReference,
            getRecommendationFailInput(),
            callback = object : RecommendationCallback {
                override fun onRecommendationsFetched(response: JSONArray) {
                    fail("Got recommendation by Module with empty input")
                }

                override fun onError(errorResponse: JSONObject) {
                    assertTrue(
                        "Get recommendation failed successfully for empty input", errorResponse != null
                    )
                    signal.countDown()
                }
            })
        signal.await(3, TimeUnit.SECONDS)
    }
}