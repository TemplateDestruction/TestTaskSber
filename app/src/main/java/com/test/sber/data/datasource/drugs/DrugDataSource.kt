package com.test.sber.data.datasource.drugs

import com.test.sber.data.api.ApiFactory
import com.test.sber.data.api.services.DataService
import com.test.sber.data.mapper.toEntity
import com.test.sber.domain.entity.Entity
import io.reactivex.Single
import javax.inject.Inject

class DrugDataSource(private val dataService: DataService): IDrugDataSource {
    override fun getRemoteData() : Single<Pair<List<Entity.Drug>, List<Entity.Drug>>> =
        Single.defer {
            dataService.getDrugList()
                .map { adultDrugListDto -> adultDrugListDto.map { DrugDto -> DrugDto.toEntity() } }
                .map { adultDrugList ->
                    val childDrugList = adultDrugList.filter { drug -> drug.isReadyForKids }
                    Pair(adultDrugList, childDrugList)
                }
        }
}