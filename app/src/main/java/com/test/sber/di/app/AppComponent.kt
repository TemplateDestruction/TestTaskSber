package com.test.sber.di.app

import com.test.sber.di.main.MainModule
import com.test.sber.di.vm.ViewModelModule
import com.test.sber.presentation.CustomApp
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        DataModule::class,
        DomainModule::class,
        AppModule::class,
        ViewModelModule::class,
        MainModule::class
    ])
@Singleton
interface AppComponent : AndroidInjector<CustomApp> {

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<CustomApp>
}