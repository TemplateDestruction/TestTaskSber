package com.test.sber.domain.usecase

import com.test.sber.domain.entity.Entity
import com.test.sber.domain.model.Drug
import com.test.sber.domain.repository.IDataRepository
import io.reactivex.Single
import javax.inject.Inject

class GetDrugListUseCase @Inject constructor(private val repository: IDataRepository) : IGetDrugListUseCase {
    override fun getDrugs(): Single<Pair<List<Entity.Drug>, List<Entity.Drug>>>
            = repository.getDrugList()
}