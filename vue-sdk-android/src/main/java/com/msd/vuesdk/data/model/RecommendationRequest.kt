package com.msd.vuesdk.data.model

import androidx.annotation.Keep
import org.json.JSONObject

@Keep
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

@Keep
data class Config(
    val conditions: List<Condition?>?= null,
    val params: List<Param?>?=null
)
@Keep
data class Condition(
    val `field`: String?=null,
    val `operator`: String?=null,
    val value: List<String?>?=null
)
@Keep
data class Param(
    val `field`: String?=null,
    val type: String?=null,
    val value: List<String?>?=null
)