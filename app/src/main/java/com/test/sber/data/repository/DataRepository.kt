package com.test.sber.data.repository

import com.test.sber.data.api.ApiFactory
import com.test.sber.domain.model.Drug
import com.test.sber.domain.repository.IDataRepository
import io.reactivex.Single


class DataRepository
//    : IDataRepository
{

//    override fun getDrugList(): Single<Pair<List<Drug>, List<Drug>>> =
//            Single.defer {
//                ApiFactory
//                    .getDataService()
//                    .getDrugList()
//                    .map { adultDrugList ->
//                        val childDrugList
//                                = adultDrugList.filter { drug -> drug.isReadyForKids }
//                        Pair(adultDrugList, childDrugList)
//                    }
//            }
}