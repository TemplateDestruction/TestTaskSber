package com.test.sber.presentation.view.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.widget.SearchView
import com.google.android.material.appbar.MaterialToolbar
import com.test.sber.R
import com.test.sber.presentation.view.base.activity.BaseFragmentActivity
import com.test.sber.presentation.vm.main.MainActivityVm
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseFragmentActivity<MainActivityVm>(R.layout.activity_main) {

    override fun getVmClass() = MainActivityVm::class.java


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            setSupportActionBar(app_bar_main)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(false)
                setDisplayShowHomeEnabled(false)
                title = ""
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment())
            .commit()
    }
}