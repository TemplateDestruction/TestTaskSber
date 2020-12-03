package com.test.sber.di

import com.test.sber.data.api.services.DataService
import com.test.sber.data.datasource.drugs.DrugDataSource
import com.test.sber.data.datasource.drugs.IDrugDataSource
import com.test.sber.data.repository.DataRepositoryNew
import com.test.sber.domain.repository.IDataRepository
import com.test.sber.domain.usecase.GetDrugListUseCase
import com.test.sber.domain.usecase.IGetDrugListUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {


    @Provides
    fun provideIGetDrugListUseCase(iDataRepository: IDataRepository) : IGetDrugListUseCase
            = GetDrugListUseCase(iDataRepository)

    @Provides
    fun provideIDataRepository(iDrugDataSource: IDrugDataSource) : IDataRepository
            = DataRepositoryNew(iDrugDataSource)

    @Provides
    fun provideIDrugDataSource(dataService: DataService) : IDrugDataSource
            = DrugDataSource(dataService)


}