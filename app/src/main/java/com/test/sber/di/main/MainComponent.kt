package com.test.sber.di.main

import com.test.sber.di.app.AppComponent
import com.test.sber.presentation.view.main.DrugFragment
import com.test.sber.presentation.view.main.MainActivity
import com.test.sber.presentation.view.main.MainFragment
import dagger.Component

@MainScope
@Component(modules = [MainModule::class]
    , dependencies = [AppComponent::class]
)
interface MainComponent {

    fun injectMainFragment(mainFragment: MainFragment)

    fun injectMainActivity(mainActivity: MainActivity)
//
    fun injectDrugFragment(drugFragment: DrugFragment)
}