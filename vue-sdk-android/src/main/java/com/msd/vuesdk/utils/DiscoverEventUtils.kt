package com.msd.vuesdk.utils

class DiscoverEventUtils {
    val discoverEventsLookup = HashMap<String, HashMap<String, String>>()
    var fetchSuccess = false

    companion object {
        private var instance: DiscoverEventUtils? = null
        fun getDiscoveryUtilInstance(): DiscoverEventUtils {
            if(instance == null){
                instance = DiscoverEventUtils()
            }
            return instance!!
        }
    }
}