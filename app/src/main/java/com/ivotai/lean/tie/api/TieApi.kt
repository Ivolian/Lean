package com.ivotai.lean.tie.api

import com.ivotai.lean.tie.dto.TieDTO
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.QueryName

interface TieApi {

    @POST("tie/add")
    fun add(@Body tieDTO: TieDTO): Single<TieDTO>

    @GET("tie/all")
    fun loadPage(@QueryName pageNo: Int): Single<List<TieDTO>>

}