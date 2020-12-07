package com.test.sber.domain.usecase.interfaces

import com.test.sber.domain.entity.Entity
import com.test.sber.domain.model.Drug
import io.reactivex.Single

interface ICheckConnectionUseCase {
    fun checkConnection() : Single<Boolean>
}