package com.ivotai.lean.user.useCase

import com.ivotai.lean.app.base.ViewState1
import com.ivotai.lean.app.di.ComponentsHolder
import com.ivotai.lean.user.po.User
import io.reactivex.functions.Consumer

class LoadUserUseCase(consumer: Consumer<ViewState1<List<User>>>)  {

    init {
        ComponentsHolder.userComponent.getUserRepo()
                .fetchFromNetwork()
                .toObservable()
                .map { ViewState1(data = it) }
                .onErrorReturn { ViewState1(error = it) }
                .startWith(ViewState1())
                .subscribe(consumer)
    }

}