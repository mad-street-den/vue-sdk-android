package com.msd.vuesampleapp.repository

import org.json.JSONObject

class TrackUserRepository() {
    private val msdClient = com.msd.vuesampleapp.data.controller.VueSDKController.getVueSDKInstance()

    fun pageView() {
        msdClient?.track(
            "PageView",
            properties = JSONObject("""{"page_type": "pdp", "page_name": "PDP", "product_id": "39596296700022"}"""),
        )
    }

    fun orderConfirmation() {
        val properties =
            JSONObject(
                """{"page_type":"oc", "page_name": "Order Confirmation", "product_price": [100.00], "price": ["100.00"], "product_id": ["39596296700022"],"order_id": "AE75634","quantity": [10]}"""
            )
        msdClient?.track(
            "Buy",
            properties,
        )
    }

    fun rightSwipe() {
        val properties =
            JSONObject(
                """ {
                "page_type": "pdp",
                "page_name": "PDP",
                "product_id": "5789256482843",
                "slot_id": "android_slot2",
                "module_id": "a5777370-b133-426a-ae3a-5a883a787130"
            }"""
            )
        msdClient?.track(
            "rightSwipe",
            properties,
        )
    }
    fun moduleClick() {
        val properties =
            JSONObject(
                """ {
            "page_type": "pdp",
            "page_name": "PDP",
            "product_id": "5789256482843",
            "clicked_product_id": "39946630725750",
            "position_of_reco": 1,
            "slot_id": "android_slot2",
            "module_id": "a5777370-b133-426a-ae3a-5a883a787130",
            "strategy_id": "04092a30-22e2-4565-83ee-3ffd83cb6375"
        }"""
            )
        msdClient?.track(
            "ModuleClick",
            properties,
        )
    }
    fun placeOrder() {
        val properties =
            JSONObject(
                """{
            "page_type": "pdp",
            "page_name": "PDP",
            "product_id": "5789256482843"
        }"""
            )
        msdClient?.track(
            "placeOrder",
            properties,
        )
    }
    fun moduleView() {
        val properties =
            JSONObject(
                """ {
            "page_type": "pdp",
            "page_name": "PDP",
            "product_id": "5789256482843",
            "slot_id": "android_slot2",
            "module_id": "a5777370-b133-426a-ae3a-5a883a787130"
        }"""
            )
        msdClient?.track(
            "ModuleView",
            properties,
        )
    }
    fun homePageView() {
        val properties =
            JSONObject(
                """  {
            "page_type": "Home",
            "page_name": "Home"
        }"""
            )
        msdClient?.track(
            "PageView",
            properties,
        )
    }
    fun addToCart() {
        val properties =
            JSONObject(
                """   {
            "page_type": "pdp",
            "page_name": "PDP",
            "product_id": "5789256482843",
            "clicked_product_id": "39946630725750"
        }"""
            )
        msdClient?.track(
            "Add2cart",
            properties,
        )
    }
    fun removeFromCart() {
        val properties =
            JSONObject(
                """ {
            "page_type": "pdp",
            "page_name": "PDP",
            "product_id": "5789256482843",
            "clicked_product_id": "39946630725750"
        }"""
            )
        msdClient?.track(
            "Removefromcart",
            properties,
        )
    }
    fun leftSwipe() {
        val properties =
            JSONObject(
                """ {
            "page_type": "pdp",
            "page_name": "PDP",
            "product_id": "5789256482843",
            "slot_id": "android_slot2",
            "module_id": "a5777370-b133-426a-ae3a-5a883a787130"
        }"""
            )
        msdClient?.track(
            "leftSwipe",
            properties,
        )
    }
}