package com.test.sber.presentation.view.base.fragment

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.test.sber.presentation.vm.base.BaseVm

/**
 * Базовый Fragment с [BaseVm] классом
 *
 * EN: Base Fragment with [BaseVm] class
 */
abstract class BaseVmFragment<VM : BaseVm> : BaseFragment() {

    /**
     * Переопределить если понадобится создать фабрику VM
     */
    protected open val vmFactory: ViewModelProvider.Factory? = null

    /**
     * ViewModel для Fragment
     */
    val vm: VM by lazy {
        if (vmFactory == null)
            ViewModelProvider(this)
                .get(getVmClass())
        else
            (vmFactory as ViewModelProvider.Factory).create(getVmClass())
    }

    protected abstract fun getVmClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.createVmBinds()
    }
}