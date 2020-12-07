package com.test.sber.presentation

import android.app.Application
import com.test.sber.di.app.AppComponent
import com.test.sber.di.app.AppModule
import com.test.sber.di.app.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class CustomApp : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}