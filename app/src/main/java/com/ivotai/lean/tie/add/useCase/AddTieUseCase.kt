package com.ivotai.lean.tie.add.useCase

import com.ivotai.lean.app.Current
import com.ivotai.lean.app.base.ViewState1
import com.ivotai.lean.app.di.ComponentsHolder
import com.ivotai.lean.tie.dto.TieDTO
import io.reactivex.functions.Consumer

class AddTieUseCase(content: String, pic: String = "", consumer: Consumer<ViewState1<TieDTO>>) {

    init {
        Current.user?.let { TieDTO(content = content, pic = pic, poster = it) }
                ?.let {
                    ComponentsHolder.userComponent.getTieRepo()
                            .addTie(it)
                            .toObservable()
                            .map { ViewState1(data = it) }
                            .onErrorReturn { ViewState1(error = it) }
                            .startWith(ViewState1())
                            .subscribe(consumer)
                }
    }

}