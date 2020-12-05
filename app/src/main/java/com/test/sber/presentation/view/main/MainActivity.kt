package com.test.sber.presentation.view.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.MaterialToolbar
import com.test.sber.R
import com.test.sber.di.main.DaggerMainComponent
import com.test.sber.di.main.MainModule
import com.test.sber.presentation.CustomApp
import com.test.sber.presentation.view.base.activity.BaseFragmentActivity
import com.test.sber.presentation.vm.main.MainActivityVm
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseFragmentActivity<MainActivityVm>(R.layout.activity_main) {

    override fun getVmClass() = MainActivityVm::class.java

    @Inject
    override lateinit var vmFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerMainComponent
            .builder()
            .appComponent(CustomApp.appComponent)
            .mainModule(MainModule(this))
            .build()
            .injectMainActivity(this)

        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            setSupportActionBar(app_bar_main)
            supportActionBar?.title = ""
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment())
            .commit()
    }
}