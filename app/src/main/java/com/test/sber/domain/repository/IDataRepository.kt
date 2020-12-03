package com.test.sber.domain.repository

import com.test.sber.domain.entity.Entity
import com.test.sber.domain.model.Drug
import io.reactivex.Single

interface IDataRepository {
    fun getDrugList(): Single<Pair<List<Entity.Drug>, List<Entity.Drug>>>
}