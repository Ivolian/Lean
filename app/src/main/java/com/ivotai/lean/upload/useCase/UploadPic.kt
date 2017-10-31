package com.ivotai.lean.upload.useCase

import com.ivotai.lean.app.base.ViewState1
import com.ivotai.lean.app.di.ComponentsHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class UploadPic(picPath: String, consumer: Consumer<ViewState1<String>>) {

    init {
        val picFile = File(picPath)
        val body = RequestBody.create(MediaType.parse("image/jpg"), picFile)
        val part = MultipartBody.Part.createFormData("pic", "_", body)
        ComponentsHolder.userComponent.getUploadApi()
                .uploadPic(part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter { it.success }
                .map { it.fileName }

                .toObservable()
                .map { ViewState1(data = it) }
                .onErrorReturn { ViewState1(error = it) }
                .subscribe { consumer.accept(it) }
    }

}