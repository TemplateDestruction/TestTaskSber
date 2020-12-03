package com.test.sber.presentation.vm.main

import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.test.sber.data.extension.applyScheduler
import com.test.sber.data.repository.RepositoryProvider
import com.test.sber.di.DaggerAppComponent
import com.test.sber.domain.entity.Entity
import com.test.sber.domain.model.Drug
import com.test.sber.domain.usecase.GetDrugListUseCase
import com.test.sber.domain.usecase.IGetDrugListUseCase
import com.test.sber.presentation.vm.base.BaseVm
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainFragmentVm : BaseVm() {
    val drugListState
            = BehaviorRelay.createDefault<Pair<List<Entity.Drug>, List<Entity.Drug>>>(Pair(emptyList(), emptyList()))
    val loadingState = BehaviorRelay.createDefault(false)
    val internetErrorState = PublishRelay.create<Unit>()

    @Inject lateinit var iDrugListUseCase: IGetDrugListUseCase
    fun init() {
        DaggerAppComponent
            .builder()
            .build()
            .injectViewModel(this)
        loadContent()
    }

    private fun loadContent() {
        iDrugListUseCase
            .getDrugs()
            .applyScheduler(Schedulers.io())
            .doOnSubscribe {
                loadingState.accept(true)
            }
            .doAfterTerminate {
                loadingState.accept(false)
            }
            .subscribe(
                { drugListState.accept(it) }, { e -> internetErrorState.accept(Unit) }
            )
            .addTo(binds)
    }

    override fun onCleared() {
        binds.clear()
        super.onCleared()
    }

}
