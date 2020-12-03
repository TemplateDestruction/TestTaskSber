package com.test.sber.presentation.vm.main

import android.util.Log
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
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainFragmentVm : BaseVm() {
    val drugListState = PublishRelay.create<Pair<List<Entity.Drug>, List<Entity.Drug>>>(
//        Pair(
//            emptyList(),
//            emptyList()
//        )
    )
    val loadingState = PublishRelay.create<Boolean>()
    val internetErrorState = PublishRelay.create<Unit>()

    @Inject
    lateinit var iDrugListUseCase: IGetDrugListUseCase
    fun init() {
        DaggerAppComponent
            .builder()
            .build()
            .injectViewModel(this)
        loadContent()
    }

    fun loadContent() {
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
                { its ->
                    Completable
                        .timer(1, TimeUnit.SECONDS)
                        .applyScheduler(Schedulers.io())
                        .subscribe {
                            drugListState.accept(its)
                        }
                }, { e ->
                    internetErrorState.accept(Unit)
                }
            )
            .addTo(binds)
    }

    override fun onCleared() {
        binds.clear()
        super.onCleared()
    }

}
