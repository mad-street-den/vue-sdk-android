package com.msd.sdk.data.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {

    companion object {
        private var retrofit: Retrofit? = null
        private var BASE_URL_MSD = "https://api-dev.madstreetden.xyz"
        @JvmStatic
        fun getRetrofitInstance(): Retrofit? {
            if (retrofit == null) {
                val okHttpClient = OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build()
                retrofit = Retrofit.Builder().baseUrl(BASE_URL_MSD
                )
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            }
            return retrofit
        }
    }
}