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
                    "title",
                    "price",
                    "image_link",
                    "link"
                ],
                "context": {
                    "variant_id": "39596296700022"
                }
            }
        }""".trimIndent()
        )
        return RecommendationRequest(catalogs = recommendationInput)
    }

    fun getRecommendationsByPage() {
        msdClient?.getRecommendationsByPage("PDP",
            getRecommendationInput("3089e3d3ba"),
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
             "3089e3d3ba": {
                "fields": [
                    "title",
                    "price",
                    "image_link",
                    "link"
                ],
                "context": {
                    "variant_id": "5789256482843"
                },
                "facets": [
                    "product_type"
                ],
                "facet_limit": 100,
                "search_query": "top",
                "search_fields": [
                    "title"
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
        msdClient?.getRecommendationsByModule("Similar Products Module - 27 June",
            getRecommendationInput("427e26dbfa"),
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
             "3089e3d3ba": {
                "fields": [
                    "title",
                    "price",
                    "image_link",
                    "link"
                ],
                "context": {
                    "variant_id": "5789256482843"
                },
                "search_query": "top",
                "search_fields": [
                    "title"
                ],
                "facets": [
                    "product_type",
                    "gender"
                ]
            }
        }""".trimIndent()
        )
        val recommendationCatalogs = RecommendationRequest(catalogs = recommendationInput)
        msdClient?.getRecommendationsByModule("Search New",
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
        msdClient?.getRecommendationsByStrategy("Similar Products - 27June",
            getRecommendationInput("427e26dbfa"),
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