package com.msd.vuesdk.recommendationtests

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.msd.vuesdk.BaseInstrumentedTest
import com.msd.vuesdk.constants.getRecommendationFailInput
import com.msd.vuesdk.constants.getRecommendationSuccessInput
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
class GetRecommendationStrategyTest() : BaseInstrumentedTest() {
    private val strategyReference = "Similar Strategy - June30"

    @Test
    fun `testRecommendationsByStrategy-Get_recommendations_by_strategy_success`() {
        val signal = CountDownLatch(1)
        vueSDK?.getRecommendationsByStrategy(strategyReference,
            getRecommendationSuccessInput("427e26dbfa"),
            callback = object : RecommendationCallback {
                override fun onRecommendationsFetched(response: JSONArray) {
                    assertTrue("Get recommendations response is null", response.length() > 0)
                    signal.countDown()
                }

                override fun onError(errorResponse: JSONObject) {
                    fail("Failed to get recommendation by Strategy")
                }
            })
        signal.await(3, TimeUnit.SECONDS)
    }

    @Test
    fun `testRecommendationsByStrategy-Get_recommendations_by_strategy_failure`() {
        val signal = CountDownLatch(1)
        vueSDK?.getRecommendationsByStrategy(strategyReference,
            getRecommendationFailInput(),
            callback = object : RecommendationCallback {
                override fun onRecommendationsFetched(response: JSONArray) {
                    fail("Got recommendation by Strategy with empty input")
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