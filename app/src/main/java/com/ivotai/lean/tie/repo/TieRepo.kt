package com.ivotai.lean.tie.repo

import com.ivotai.lean.tie.api.TieApi
import com.ivotai.lean.tie.dto.TieWrapper
import com.ivotai.lean.tie.po.Tie
import com.ivotai.lean.user.di.UserScope
import io.objectbox.Box
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@UserScope
class TieRepo @Inject constructor(val tieApi: TieApi, tieBox: Box<Tie>) {

    fun createTie(tie: Tie) {
        createTie(TieWrapper.fromTie(tie))
    }

    fun createTie(wrapper: TieWrapper) {
        tieApi.add(wrapper)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    ""
                }, {
                    ""
                })
    }

}

