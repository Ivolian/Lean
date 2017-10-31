package com.ivotai.lean.tie.add

import com.ivotai.lean.Current
import com.ivotai.lean.app.base.ViewState1
import com.ivotai.lean.tie.dto.TieWrapper
import com.ivotai.lean.tie.repo.TieRepo
import com.ivotai.lean.tie.ui.AddTieView

class AddTie(addTieView: AddTieView,content: String, tieRepo: TieRepo) {

    init {
        TieWrapper(content = content, poster = Current.user!!).let {
            tieRepo.addTie(it)
                    .toObservable()
                    .map { ViewState1(data = it) }
                    .onErrorReturn { ViewState1(error = it) }
                    .startWith(ViewState1())
                    .subscribe { addTieView.backToTieView(it) }
        }
    }

}