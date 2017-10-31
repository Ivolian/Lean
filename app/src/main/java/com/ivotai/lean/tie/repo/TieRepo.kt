package com.ivotai.lean.tie.repo

import com.ivotai.lean.tie.api.TieApi
import com.ivotai.lean.tie.dto.TieWrapper
import com.ivotai.lean.tie.po.Tie
import io.objectbox.Box
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class TieRepo @Inject constructor( val tieApi: TieApi, private val tieBox: Box<Tie>) {

//    fun getTies(): Observable<List<Tie>> {
//        val db = loadFromDb()
//        val network = fetchFromNetwork()
//        return network.concatWith(db).take(1)
//    }

//    private fun loadFromDb() = RxQuery
//            .observable(tieBox.query().build())
//            .observeOn(AndroidSchedulers.mainThread())

    fun loadPage(pageNo: Int): Single<List<Tie>> = tieApi.loadPage(pageNo)
            .delay(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map { wrappers -> wrappers.map { TieWrapper.toTie(it) } }
            .doOnSuccess { tieBox.put(it) }

    fun addTie(tieWrapper: TieWrapper) = tieApi.add(tieWrapper)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

}

