package com.test.sber.di.vm

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
    @Singleton
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentVm::class)
    abstract fun bindMainFragmentVm(viewModel: MainFragmentVm): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DrugFragmentVm::class)
    abstract fun bindDrugFragmentVm(viewModel: DrugFragmentVm): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityVm::class)
    abstract fun bindMainActivityVm(viewModel: MainActivityVm): ViewModel
}