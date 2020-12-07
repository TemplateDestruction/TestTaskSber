package com.test.sber.data.repository

import android.content.Context
import com.test.sber.data.datasource.drugs.IDrugDataSource
import com.test.sber.domain.entity.Entity
import com.test.sber.domain.repository.IDataRepository
import io.reactivex.Single


class DataRepository(
    private val drugDataSource: IDrugDataSource,
    private val appContext: Context
) : IDataRepository {

    override fun getDrugList(): Single<Pair<List<Entity.Drug>, List<Entity.Drug>>>
            = drugDataSource.getRemoteData()

    override fun checkConnection(): Single<Boolean>
            = InternetConnectionUtil.isInternetOn(appContext)
}