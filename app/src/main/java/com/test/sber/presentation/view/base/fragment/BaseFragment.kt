package com.test.sber.presentation.view.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

/**
 * Базовый класс включающий в себя общую для всех Fragment логику в проекте.
 */
abstract class BaseFragment : Fragment() {

    protected var firstStart: Boolean = true

    protected val rxBinds = CompositeDisposable()

    /**
     * Метод вызывается один раз при старте фрагмента
     * Здесь можно обработать переданные в аргументах данные
     */
    protected open fun init() {}


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

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

    protected open fun createBinds() {
    }

}