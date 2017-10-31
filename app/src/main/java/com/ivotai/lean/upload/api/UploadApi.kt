package com.ivotai.lean.upload.api

import com.ivotai.lean.upload.dto.UploadResult
import io.reactivex.Single
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface UploadApi {

    @Multipart
    @POST("upload/pic")
    fun uploadPic(@Part part: MultipartBody.Part): Single<UploadResult>

}