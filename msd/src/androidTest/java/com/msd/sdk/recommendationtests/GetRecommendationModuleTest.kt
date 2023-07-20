package com.msd.sdk.recommendationtests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.msd.sdk.BaseInstrumentedTest
import com.msd.sdk.constants.getRecommendationFailInput
import com.msd.sdk.constants.getRecommendationSuccessInput
import com.msd.sdk.helper.client.callbacks.RecommendationCallback
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
    private val moduleReference = "Similar Products"

    @Test
    fun `testRecommendationsByModule-Get_recommendations_by_module_success`() {
        val signal = CountDownLatch(1)
        msd?.getRecommendationsByModule(moduleReference,
            getRecommendationSuccessInput("427e26dbfa"),
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

        msd?.getRecommendationsByModule(moduleReference,
            getRecommendationFailInput(),
            callback = object : RecommendationCallback {
                override fun onRecommendationsFetched(response: JSONArray) {
                    assertTrue(
                        "Get recommendations response is not null", response.length() == 0
                    )
                    signal.countDown()
                }

                override fun onError(errorResponse: JSONObject) {
                    fail("Failed to get recommendation by Module")
                }
            })
        signal.await(3, TimeUnit.SECONDS)
    }
}