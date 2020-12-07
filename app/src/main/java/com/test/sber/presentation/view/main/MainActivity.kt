package com.test.sber.presentation.view.main

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.test.sber.R
import com.test.sber.presentation.view.base.activity.BaseFragmentActivity
import com.test.sber.presentation.vm.main.MainActivityVm
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseFragmentActivity<MainActivityVm>(R.layout.activity_main) {

    override fun getVmClass() = MainActivityVm::class.java

    override fun getNavControllerId(): Int = R.id.nav_host_fragment

    @Inject
    override lateinit var vmFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}