package com.test.sber.di.app

import android.content.Context
import com.test.sber.data.api.services.DataService
import com.test.sber.data.datasource.drugs.DrugDataSource
import com.test.sber.data.datasource.drugs.IDrugDataSource
import com.test.sber.data.repository.DataRepository
import com.test.sber.domain.repository.IDataRepository
import com.test.sber.domain.usecase.implementations.CheckConnectionUseCase
import com.test.sber.domain.usecase.implementations.GetDrugListUseCase
import com.test.sber.domain.usecase.interfaces.ICheckConnectionUseCase
import com.test.sber.domain.usecase.interfaces.IGetDrugListUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {


    @Provides
    @Singleton
    fun provideIGetDrugListUseCase(iDataRepository: IDataRepository) : IGetDrugListUseCase
            = GetDrugListUseCase(
        iDataRepository
    )

    @Provides
    @Singleton
    fun provideICheckConnectionUseCase(iDataRepository: IDataRepository) : ICheckConnectionUseCase
            = CheckConnectionUseCase(iDataRepository)

    @Provides
    @Singleton
    fun provideIDataRepository(iDrugDataSource: IDrugDataSource, appContext: Context) : IDataRepository
            = DataRepository(iDrugDataSource, appContext)

    @Provides
    @Singleton
    fun provideIDrugDataSource(dataService: DataService) : IDrugDataSource
            = DrugDataSource(dataService)





}