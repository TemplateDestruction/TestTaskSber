package com.test.sber.presentation.vm.main

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.test.sber.data.extension.applyScheduler
import com.test.sber.di.main.MainScope
import com.test.sber.domain.entity.Entity
import com.test.sber.domain.usecase.IGetDrugListUseCase
import com.test.sber.presentation.CustomApp
import com.test.sber.presentation.vm.base.BaseVm
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainFragmentVm @Inject constructor(private val iGetDrugListUseCase: IGetDrugListUseCase) : BaseVm() {
    val drugListState
            = BehaviorRelay.createDefault<Pair<List<Entity.Drug>, List<Entity.Drug>>>(
        Pair(
            emptyList(),
            emptyList()
        )
    )
    val loadingState
            = BehaviorRelay.createDefault<Boolean>(false)
    val internetErrorState
            = PublishRelay.create<Unit>()


    fun init() {
//        CustomApp.appComponent.injectViewModel(this)
        loadContent()
    }

    fun loadContent() {
        iGetDrugListUseCase
            .getDrugs()
            .applyScheduler(Schedulers.io())
            .doOnSubscribe { loadingState.accept(true) }
            .doAfterTerminate { loadingState.accept(false) }
            .subscribe(
                { drugListState.accept(it) },
                { internetErrorState.accept(Unit) }
            )
            .addTo(binds)
    }

    override fun onCleared() {
        binds.clear()
        super.onCleared()
    }

}
