package com.test.sber.di.main

import com.test.sber.di.main.scopes.MainFragmentScope
import com.test.sber.presentation.view.adapters.DrugAdapter
import com.test.sber.presentation.view.adapters.DrugsPagerAdapter
import com.test.sber.presentation.view.main.MainFragment
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @MainFragmentScope
    @Provides
    fun provideDrugsPagerAdapter(mainFragment: MainFragment, adultAdapter: DrugAdapter, childAdapter : DrugAdapter)
            = DrugsPagerAdapter(mainFragment.requireContext(), adultAdapter, childAdapter)
}