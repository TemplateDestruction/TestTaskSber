package com.test.sber.data.api

import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class LoggingInterceptor private constructor() : Interceptor {



    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY).intercept(chain)
    }

    companion object {
        fun create(): Interceptor {
            return LoggingInterceptor()
        }
    }
}