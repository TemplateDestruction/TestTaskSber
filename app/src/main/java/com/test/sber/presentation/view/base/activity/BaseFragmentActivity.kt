package com.test.sber.presentation.view.base.activity

import androidx.annotation.LayoutRes
import com.test.sber.presentation.vm.base.BaseVm

/**
 * Класс в котором реализуется общая логика для Activity в котором
 * меняются Fragments
 */
abstract class BaseFragmentActivity<VM : BaseVm>(
    @LayoutRes layoutRes: Int? = null
) : BaseVmActivity<VM>(layoutRes)