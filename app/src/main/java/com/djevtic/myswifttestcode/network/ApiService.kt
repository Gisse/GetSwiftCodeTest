package com.djevtic.myswifttestcode.network

import com.djevtic.myswifttestcode.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {

    private var retrofit: Retrofit? = null

    fun getSwiftClient(): Retrofit {
        return getClient(BuildConfig.SWIFT_SERVER)
    }

    private fun getClient(serverURI: String): Retrofit {
        if (retrofit == null || retrofit!!.baseUrl().toString() != serverURI) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder()

            if (BuildConfig.DEBUG) {
                httpClient.addInterceptor(loggingInterceptor)
            }
            httpClient.connectTimeout(BuildConfig.CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
            httpClient.writeTimeout(BuildConfig.WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            httpClient.readTimeout(BuildConfig.READ_TIMEOUT.toLong(), TimeUnit.SECONDS)

            retrofit = Retrofit.Builder()
                .baseUrl(serverURI)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }
        return retrofit!!
    }
}