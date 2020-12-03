package com.test.sber.presentation.vm.main

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.test.sber.data.repository.RepositoryProvider
import com.test.sber.domain.model.Drug
import com.test.sber.presentation.vm.base.BaseVm
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class DrugFragmentVm() : BaseVm() {


    fun init() {

    }

    override fun onCleared() {
        binds.clear()
        super.onCleared()
    }

}
