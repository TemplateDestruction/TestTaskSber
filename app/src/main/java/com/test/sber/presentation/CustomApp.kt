package com.test.sber.presentation

import android.app.Application
import com.test.sber.di.app.AppComponent
import com.test.sber.di.app.AppModule
import com.test.sber.di.app.DaggerAppComponent

class CustomApp : Application() {


    override fun onCreate() {
        super.onCreate()
        appComponent = buildComponent()
    }

    companion object {
        @JvmStatic
        lateinit var appComponent : AppComponent
    }

    private fun buildComponent() : AppComponent {
        return DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}