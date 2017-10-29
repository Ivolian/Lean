package com.ivotai.lean.tie.api

import com.ivotai.lean.tie.dto.TieWrapper
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface TieApi {

    @POST("tie/add")
    fun add(@Body tieWrapper: TieWrapper): Single<TieWrapper>

    @GET("tie/all")
    fun loadPage(pageNo: Int): Single<List<TieWrapper>>

}