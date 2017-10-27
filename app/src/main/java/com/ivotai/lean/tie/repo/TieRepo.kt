package com.ivotai.lean.tie.repo

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import com.ivotai.lean.app.base.Resource
import com.ivotai.lean.app.base.Source
import com.ivotai.lean.app.base.Status
import com.ivotai.lean.tie.api.TieApi
import com.ivotai.lean.tie.dto.TieWrapper
import com.ivotai.lean.tie.po.Tie
import com.ivotai.lean.user.di.UserScope
import io.objectbox.Box
import io.objectbox.rx.RxQuery
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@UserScope
class TieRepo @Inject constructor(private val tieApi: TieApi, private val tieBox: Box<Tie>) {

    private val ties = MediatorLiveData<Resource<List<Tie>>>()


    fun loadTies() = ties.apply {
        if (value != null && value!!.isSuccess()) {
            return@apply
        }
        watchDbSource()
    }

    private fun watchDbSource() = ties.apply {
        value = Resource(Status.LOADING, Source.DB)
        val dbSource = loadFromDb()
        addSource(dbSource, { ties ->
            ties!!
            removeSource(dbSource)
            if (false) {
                value = Resource(Status.SUCCESS, Source.DB, ties)
            } else {
                watchNetworkSource()
            }
        })
    }

    private fun loadFromDb() = MutableLiveData<List<Tie>>().apply {
        val query = tieBox.query().build()
        RxQuery.observable(query)
                // 模拟读取数据
//                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { value = it },
                        { com.orhanobut.logger.Logger.e(it, "") }
                )
    }

    private fun watchNetworkSource() = ties.apply {
        val networkSource = fetchFromNetwork()
        addSource(networkSource, { resource ->
            value = resource!!
            if (!resource.isLoading()) {
                removeSource(networkSource)
            }
        })
    }

    private fun fetchFromNetwork() = MutableLiveData<Resource<List<Tie>>>().apply {
        value = Resource(Status.LOADING, Source.NETWORK)
        tieApi.all()
//                // 模拟读取数据
//                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { wrappers -> wrappers.map { TieWrapper.toTie(it) } }
                .subscribe(
                        {
                            value = Resource(Status.SUCCESS, Source.NETWORK, it)
                            // 持久化
                            tieBox.put(it)
                        },
                        { value = Resource(Status.ERROR, Source.NETWORK, it) }
                )
    }
//    fun createTie(wrapper: TieWrapper) {
//        tieApi.add(wrapper)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    ""
//                }, {
//                    ""
//                })
//    }

}

