package com.test.sber.di

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.test.sber.data.api.ApiConfig
import com.test.sber.data.api.LoggingInterceptor
import com.test.sber.data.api.services.DataService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module()
class DataModule {

    @Provides
    fun provideDataService(retrofit: Retrofit): DataService {
        return retrofit.create(DataService::class.java)
    }

    @Provides
    fun provideRetrofit(httpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConfig.apiUrl)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    fun provideOkHttpClient(loggingInterceptor: Interceptor) : OkHttpClient {
        return OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    fun provideHttpLoggingInterceptor() : Interceptor {
        return LoggingInterceptor.create()
    }
}