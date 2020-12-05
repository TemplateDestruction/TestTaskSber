package com.test.sber.di.app

import androidx.lifecycle.ViewModelProvider
import com.test.sber.di.app.vm.ViewModelModule
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        AppModule::class,
        ViewModelModule::class
    ])
@Singleton
interface AppComponent {
      fun provideViewModelFactory() : ViewModelProvider.Factory
//    fun injectViewModel(baseVm: MainFragmentVm)
}