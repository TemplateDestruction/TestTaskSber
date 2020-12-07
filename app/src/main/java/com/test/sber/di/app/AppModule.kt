package com.test.sber.di.app

import android.content.Context
import com.test.sber.presentation.CustomApp
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideContext(customApp: CustomApp) : Context
}