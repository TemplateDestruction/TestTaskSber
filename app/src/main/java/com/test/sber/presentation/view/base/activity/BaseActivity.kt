package com.test.sber.presentation.view.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.test.sber.R
import com.test.sber.presentation.view.base.fragment.OnBackPressedListener
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable

/**
 * Содержит некоторую логику общую для всех Activity в проекте.
 */
abstract class BaseActivity(
    @LayoutRes layoutRes: Int? = null
) : DaggerAppCompatActivity(
        layoutRes ?: R.layout.empty_layout
) {

    protected val rxBinds = CompositeDisposable()

    protected var firstStart = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        if (savedInstanceState != null) firstStart = false
    }

    override fun onResume() {
        super.onResume()
        if (firstStart) {
            init()
            firstStart = false
        }
        createBinds()
    }

    override fun onPause() {
        super.onPause()
        rxBinds.clear()
    }

    override fun onBackPressed() {
        for (fragment in supportFragmentManager.fragments)
            if (fragment is OnBackPressedListener && fragment.onBackPressed())
                return
        super.onBackPressed()
    }

    protected open fun createBinds() {
    }


    /**
     * Метод вызывается один раз при старте активити
     * Здесь можно обработать переданные в Intent данные
     */
    protected open fun init() {
    }
}