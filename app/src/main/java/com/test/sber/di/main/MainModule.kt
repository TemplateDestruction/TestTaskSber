package com.test.sber.di.main

import com.test.sber.di.main.scopes.ActivityScope
import com.test.sber.di.main.scopes.DrugFragmentScope
import com.test.sber.di.main.scopes.MainFragmentScope
import com.test.sber.presentation.view.main.DrugFragment
import com.test.sber.presentation.view.main.MainActivity
import com.test.sber.presentation.view.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @MainFragmentScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun mainFragment(): MainFragment

    @DrugFragmentScope
    @ContributesAndroidInjector
    abstract fun drugFragment(): DrugFragment

}