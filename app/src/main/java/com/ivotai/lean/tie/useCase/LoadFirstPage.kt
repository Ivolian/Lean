package com.ivotai.lean.tie.useCase

import com.ivotai.lean.app.base.ViewState1
import com.ivotai.lean.tie.repo.TieRepo
import com.ivotai.lean.tie.ui.TieView

class LoadFirstPage(tieView: TieView, tieRepo: TieRepo) {

    init {
        tieRepo.loadPage(0)
                .toObservable()
                .map { ViewState1(data = it) }
                .onErrorReturn { ViewState1(error = it) }
                .startWith(ViewState1())
                .subscribe { tieView.renderFirstPage(it) }
    }

}