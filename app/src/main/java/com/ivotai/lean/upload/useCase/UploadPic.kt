package com.ivotai.lean.upload.useCase

import com.ivotai.lean.app.base.ViewState1
import com.ivotai.lean.app.di.ComponentsHolder
import com.ivotai.lean.upload.dto.UploadResult
import io.reactivex.functions.Consumer

class UploadPic(picPath: String, consumer: Consumer<ViewState1<UploadResult>>) {

    init {
        ComponentsHolder.userComponent.getUploadRepo()
                .uploadPic(picPath)
                .toObservable()
                .map { ViewState1(data = it) }
                .onErrorReturn { ViewState1(error = it) }
                .startWith(ViewState1())
                .subscribe(consumer)
    }

}