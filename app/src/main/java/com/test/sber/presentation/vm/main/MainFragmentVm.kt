package com.test.sber.presentation.vm.main

import android.util.Log
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import com.test.sber.data.extension.applyScheduler
import com.test.sber.domain.entity.Entity
import com.test.sber.domain.usecase.interfaces.ICheckConnectionUseCase
import com.test.sber.domain.usecase.interfaces.IGetDrugListUseCase
import com.test.sber.presentation.vm.base.BaseVm
import io.reactivex.Single
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainFragmentVm @Inject constructor(
    private val iGetDrugListUseCase: IGetDrugListUseCase,
    private val iCheckConnectionUseCase: ICheckConnectionUseCase
) : BaseVm() {
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
    val connectionState = BehaviorRelay.createDefault<Boolean>(true)


    fun init() {
        loadContent()
    }

    fun loadContent() {
        iGetDrugListUseCase
            .getDrugs()
            .applyScheduler(Schedulers.io())
            .doOnSubscribe {
                Log.e("TAG", "loadContent: doOnSubsc" )
                loadingState.accept(true) }
            .doAfterTerminate { loadingState.accept(false) }
            .subscribe(
                { drugList ->
                      //чтобы лучше рассмотреть анимацию загрузки
//                    Observable
//                        .timer(3, TimeUnit.SECONDS)
//                        .applyScheduler(Schedulers.io())
//                        .subscribe {
                            drugListState.accept(drugList)
//                        }
                },
                { internetErrorState.accept(Unit) }
            )
            .addTo(binds)
    }

    override fun onCleared() {
        binds.clear()
        super.onCleared()
    }

    fun checkConnection() : Single<Boolean>
       = iCheckConnectionUseCase.checkConnection()

}
