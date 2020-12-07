package com.test.sber.domain.usecase.implementations

import com.test.sber.domain.entity.Entity
import com.test.sber.domain.repository.IDataRepository
import com.test.sber.domain.usecase.interfaces.IGetDrugListUseCase
import io.reactivex.Single

class GetDrugListUseCase(private val repository: IDataRepository) :
    IGetDrugListUseCase {
    override fun getDrugs(): Single<Pair<List<Entity.Drug>, List<Entity.Drug>>>
            = repository.getDrugList()
}