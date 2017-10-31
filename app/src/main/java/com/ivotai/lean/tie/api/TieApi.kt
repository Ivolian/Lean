package com.ivotai.lean.tie.api

import com.ivotai.lean.tie.dto.TieWrapper
import com.ivotai.lean.upload.dto.UploadResult
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.*

interface TieApi {

    @POST("tie/add")
    fun add(@Body tieWrapper: TieWrapper): Single<TieWrapper>

    @GET("tie/all")
    fun loadPage(@QueryName pageNo: Int): Single<List<TieWrapper>>

    @Multipart
    @POST("tie/uploadPic")
    fun upload(@Part file: MultipartBody.Part): Observable<UploadResult>

}