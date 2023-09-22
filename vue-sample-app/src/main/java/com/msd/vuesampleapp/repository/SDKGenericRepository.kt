package com.msd.vuesampleapp.repository

class SDKGenericRepository {
    private val msdClient = com.msd.vuesampleapp.data.controller.VueSDKController.getVueSDKInstance()

    fun displayBloxUUID(): String
    {
        return msdClient?.getBloxUUID()?:""
    }

    fun setNewBloxUUID(uuid: String)
    {
         msdClient?.setBloxUUID(uuid)
    }
}