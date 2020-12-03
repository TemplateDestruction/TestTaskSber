package com.test.sber.data.api

import androidx.annotation.NonNull
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.test.sber.data.api.services.DataService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiFactory {

    fun getDataService(): DataService {
        val httpClientBuilder = clientBuilder()
        synchronized(ApiFactory::class.java) {
            return buildRetrofit(
                ApiConfig.apiUrl,
                httpClientBuilder
            ).create(DataService::class.java)
        }
    }



    private fun buildRetrofit(baseUrl: String, httpClientBuilder: OkHttpClient.Builder): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(httpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun clientBuilder(): OkHttpClient.Builder = OkHttpClient.Builder()
        .addInterceptor(LoggingInterceptor.create())
}