package com.ivotai.lean.upload.repo

import com.ivotai.lean.upload.api.UploadApi
import com.ivotai.lean.upload.dto.UploadResult
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import javax.inject.Inject

class UploadRepo @Inject constructor(private val uploadApi: UploadApi) {

    fun uploadPic(picPath: String): Single<UploadResult> {
       return File(picPath)
                .let { RequestBody.create(MediaType.parse("image/jpg"), it) }
                .let { MultipartBody.Part.createFormData("pic", "_", it) }
                .let {
                    uploadApi.uploadPic(it)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                }
    }

}