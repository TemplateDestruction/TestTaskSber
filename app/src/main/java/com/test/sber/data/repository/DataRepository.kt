package com.test.sber.data.repository

import com.test.sber.data.api.ApiFactory
import com.test.sber.data.datasource.drugs.DrugDataSource
import com.test.sber.data.datasource.drugs.IDrugDataSource
import com.test.sber.data.mapper.toEntity
import com.test.sber.domain.entity.Entity
import com.test.sber.domain.repository.IDataRepository
import io.reactivex.Single



class DataRepository(private val drugDataSource : IDrugDataSource) : IDataRepository {

    override fun getDrugList(): Single<Pair<List<Entity.Drug>, List<Entity.Drug>>> =
        drugDataSource.getRemoteData()
}