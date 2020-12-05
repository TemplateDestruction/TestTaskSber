package com.test.sber.di.app.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.sber.presentation.vm.base.ViewModelFactory
import com.test.sber.presentation.vm.main.DrugFragmentVm
import com.test.sber.presentation.vm.main.MainActivityVm
import com.test.sber.presentation.vm.main.MainFragmentVm
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentVm::class)
    abstract fun provideMainFragmentVm(viewModel: MainFragmentVm): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DrugFragmentVm::class)
    abstract fun provideDrugFragmentVm(viewModel: DrugFragmentVm): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityVm::class)
    abstract fun provideMainActivityVm(viewModel: MainActivityVm): ViewModel
}