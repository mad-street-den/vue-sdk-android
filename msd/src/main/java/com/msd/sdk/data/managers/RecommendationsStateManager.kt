package com.msd.sdk.data.managers

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect

class RecommendationsStateManager {
    init {
        var data = MutableStateFlow(0)
    }

}