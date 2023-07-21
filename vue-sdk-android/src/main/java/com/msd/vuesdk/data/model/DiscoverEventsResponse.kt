package com.msd.vuesdk.data.model

import androidx.annotation.Keep

@Keep
data class AccountMetadata(
    val blox_api_url: String? = null,
    val client_id: String? = null,
    val created_ts: String? = null,
    val currency: String? = null,
    val currency_code: String? = null,
    val id: String? = null,
    val language: String? = null,
    val pdp_target_same: Boolean? = null,
    val updated_ts: String? = null
)

@Keep
data class DiscoverResponseData(
    val `account-metadata`: AccountMetadata? = null,
    val events: List<DiscoverEventsData>? = null,
)

@Keep
data class DiscoverEventsData(
    val action: String? = null,
    val event_meta: String? = null,
    val event_name: String? = null,
    val events_schema: List<EventsSchemaDiscoverEvents>? = null
)

@Keep
data class EventsSchemaDiscoverEvents(
    val catalog_id: String? = null,
    val catalog_key: String? = null,
    val data_type: String? = null,
    val explode_field: Boolean? = null,
    val mandatory: Boolean? = null,
    val meta: String? = null,
    val source_field: String? = null
)

@Keep
data class DiscoverEventsResponse(
    val correlation_id: Any? = null,
    val `data`: DiscoverResponseData? = null,
    val request_id: String? = null,
    val status: String? = null,
)