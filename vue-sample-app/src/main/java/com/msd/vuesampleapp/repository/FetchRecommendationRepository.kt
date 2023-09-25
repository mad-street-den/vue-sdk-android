package com.msd.vuesampleapp.repository

import android.util.Log
import com.msd.vuesdk.data.model.RecommendationRequest
import com.msd.vuesdk.helper.client.callbacks.RecommendationCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class FetchRecommendationRepository {
    private var _recommendationState = MutableStateFlow<JSONArray?>(null)
    private val msdClient = com.msd.vuesampleapp.data.controller.VueSDKController.getVueSDKInstance()
    var recommendationState = _recommendationState.asStateFlow()

    private fun getRecommendationInput(catalogId: String): RecommendationRequest {
        val recommendationInput = JSONObject(
            """{
            "$catalogId": {
            "fields": [
                "Title",
                "Variant Price",
                "Image Src",
                "Vendor",
                "Handle"
            ],
            "context": {
                "Handle": "wots9999"
            }
        }
        }""".trimIndent()
        )
        return RecommendationRequest(catalogs = recommendationInput)
    }

    fun getRecommendationsByPage() {
        msdClient?.getRecommendationsByPage("PDP",
            getRecommendationInput("9e3fd2f248"),
            object : RecommendationCallback {
                override fun onRecommendationsFetched(response: JSONArray) {
                    CoroutineScope(Dispatchers.IO).launch {
                        _recommendationState.emit(response)
                    }
                }

                override fun onError(errorResponse: JSONObject) {
                    Log.d("Recommendation get error", errorResponse.toString())
                }
            })
    }

    fun getRecommendationsByPageNSearch() {
        val recommendationInput = JSONObject(
            """{
             "9e3fd2f248": {
            "fields": [
                "Title",
                "Variant Price",
                "Image Src",
                "Vendor",
                "Handle"
            ],
            "context": {
                "Handle": "wots9999"
            },
            "facets": [
                "Vendor"
            ],
            "facet_limit": 100,
            "search_query": "top",
            "search_fields": [
                "Title"
            ]
        }
        }""".trimIndent()
        )
        val recommendationCatalogs = RecommendationRequest(catalogs = recommendationInput)
        msdClient?.getRecommendationsByPage("PDP",
            recommendationCatalogs,
            callback = object : RecommendationCallback {
                override fun onRecommendationsFetched(response: JSONArray) {
                    CoroutineScope(Dispatchers.IO).launch {
                        _recommendationState.emit(response)
                    }
                }

                override fun onError(errorResponse: JSONObject) {
                    Log.d("Recommendation get error", errorResponse.toString())
                }
            })
    }

    fun getRecommendationByModule() {
        msdClient?.getRecommendationsByModule("SP Aug 1st",
            getRecommendationInput("9e3fd2f248"),
            object : RecommendationCallback {
                override fun onRecommendationsFetched(response: JSONArray) {
                    CoroutineScope(Dispatchers.IO).launch {
                        _recommendationState.emit(response)
                    }
                }

                override fun onError(errorResponse: JSONObject) {
                    Log.d("Recommendation get error", errorResponse.toString())
                }
            })
    }

    fun getRecommendationByModuleNSearch() {
        val recommendationInput = JSONObject(
            """{
             "9e3fd2f248": {
            "fields": [
                "Title",
                "Variant Price",
                "Image Src",
                "Vendor",
                "Handle"
            ],
            "context": {
                "Handle": "wots9999"
            },
            "search_query": "top",
            "search_fields": [
                "title"
            ],
            "facets": [
                "Vendor"
            ]
        }
        }""".trimIndent()
        )
        val recommendationCatalogs = RecommendationRequest(catalogs = recommendationInput)
        msdClient?.getRecommendationsByModule("SP Aug 1st",
            recommendationCatalogs,
            object : RecommendationCallback {
                override fun onRecommendationsFetched(response: JSONArray) {
                    CoroutineScope(Dispatchers.IO).launch {
                        _recommendationState.emit(response)
                    }
                }

                override fun onError(errorResponse: JSONObject) {
                    Log.d("Recommendation get error", errorResponse.toString())
                }
            })
    }

    fun getRecommendationByStrategy() {
        msdClient?.getRecommendationsByStrategy("SP Aug 1st",
            getRecommendationInput("9e3fd2f248"),
            object : RecommendationCallback {
                override fun onRecommendationsFetched(response: JSONArray) {
                    CoroutineScope(Dispatchers.IO).launch {
                        _recommendationState.emit(response)
                    }
                }

                override fun onError(errorResponse: JSONObject) {
                    Log.d("Recommendation get error", errorResponse.toString())
                }
            })
    }
}