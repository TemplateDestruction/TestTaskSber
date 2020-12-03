package com.test.sber.di

import com.test.sber.domain.usecase.IGetDrugListUseCase
import com.test.sber.presentation.vm.base.BaseVm
import com.test.sber.presentation.vm.main.MainFragmentVm
import dagger.Component

@Component(modules = [DataModule::class, DomainModule::class])
interface AppComponent  {
    fun injectViewModel(baseVm: MainFragmentVm)
}