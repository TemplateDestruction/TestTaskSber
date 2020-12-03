package com.test.sber.data.api.services

import com.test.sber.data.model.Dto
import com.test.sber.domain.model.Drug
import io.reactivex.Single
import retrofit2.http.GET

interface DataService {
    @GET("v3/b168786f-720f-4f30-8fba-a0345d49823a")
    fun getDrugList(): Single<List<Dto.Drug>>
}