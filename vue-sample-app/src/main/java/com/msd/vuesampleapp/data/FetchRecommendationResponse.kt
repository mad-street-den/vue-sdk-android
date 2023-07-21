package com.msd.vuesampleapp.data

data class FetchRecommendationResponse(
    val data: List<com.msd.vuesampleapp.data.FetchRecommendationData>
)

data class FetchRecommendationData(
    val image_link: String?,
    val link: String?,
//    val price: Double?,
    val title: String?,
    val variant_id: String?
)