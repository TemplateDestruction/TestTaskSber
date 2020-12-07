package com.test.sber.domain.usecase.implementations

import com.test.sber.domain.repository.IDataRepository
import com.test.sber.domain.usecase.interfaces.ICheckConnectionUseCase
import io.reactivex.Single

class CheckConnectionUseCase(private val repository: IDataRepository) :
    ICheckConnectionUseCase {
    override fun checkConnection(): Single<Boolean>
        = repository.checkConnection()
}