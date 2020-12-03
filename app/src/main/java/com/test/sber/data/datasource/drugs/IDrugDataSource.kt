package com.test.sber.data.datasource.drugs

import com.test.sber.data.datasource.BaseDataSource
import com.test.sber.domain.entity.Entity
import io.reactivex.Single

interface IDrugDataSource: BaseDataSource {
    fun getRemoteData(): Single<Pair<List<Entity.Drug>, List<Entity.Drug>>>
}