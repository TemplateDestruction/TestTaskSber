package com.test.sber.presentation.view.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.test.sber.presentation.vm.base.BaseVm

/**
 * Базовая Activity с [BaseVm] классом
 */
abstract class BaseVmActivity<VM : BaseVm>(
    @LayoutRes layoutRes: Int? = null
) : BaseActivity(layoutRes) {

    /**
     * Переопределить если понадобится создать фабрику VM
     */
    protected open val vmFactory: ViewModelProvider.Factory? = null

    /**
     * ViewModel этой Activity
     */
    val vm: VM by lazy {
        if (vmFactory == null)
            ViewModelProvider(this)
                .get(getVmClass())
        else
            (vmFactory as ViewModelProvider.Factory).create(getVmClass())
    }

    /**
     * Метод для поддержки дженериков,
     * он нужен для инициализации VM (для [ViewModelProviders.of])
     */
    protected abstract fun getVmClass(): Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        vm.createVmBinds()
        super.onCreate(savedInstanceState)
    }
}