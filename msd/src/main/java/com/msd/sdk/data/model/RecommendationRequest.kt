package com.msd.sdk.data.model

import org.json.JSONObject

data class RecommendationRequest(
    val catalogs: JSONObject,
    val config: List<Config?>? = null,
    val explain: Boolean? = null,
    val max_bundles: Int? = null,
    val max_content: String? = null,
    val min_bundles: Int? = null,
    val min_content: String? = null,
    val page_num: Int? = null,
    val skip_cache: Boolean? = null,
    val unbundle: Boolean? = null
    )


data class Config(
    val conditions: List<Condition?>?,
    val params: List<Param?>?
)

data class Condition(
    val `field`: String?,
    val `operator`: String?,
    val value: List<String?>?
)

data class Param(
    val `field`: String?,
    val type: String?,
    val value: List<String?>?
)