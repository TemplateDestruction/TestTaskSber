package com.test.sber.di.main

import android.content.Context
import androidx.viewpager.widget.PagerAdapter
import com.test.sber.di.main.MainScope
import com.test.sber.presentation.view.adapters.DrugAdapter
import com.test.sber.presentation.view.adapters.DrugsPagerAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class MainModule(private val mContext: Context) {

    @Provides
    @MainScope
    fun provideDrugsPagerAdapter(adultAdapter: DrugAdapter, childAdapter : DrugAdapter)
            = DrugsPagerAdapter(mContext, adultAdapter, childAdapter)


}