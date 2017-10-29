package com.ivotai.lean.tie.repo

import android.arch.lifecycle.MediatorLiveData
import com.ivotai.lean.tie.api.TieApi
import com.ivotai.lean.tie.po.Tie
import io.objectbox.Box
import javax.inject.Inject

class TieRepo @Inject constructor(private val tieApi: TieApi, private val tieBox: Box<Tie>) {

    private val ties = MediatorLiveData<ViewState<List<Tie>>>()


    fun loadTies() = ties.apply {
        if (value != null && value!!.isSuccess()) {
            return@apply
        }
//        watchDbSource()
    }

//    private fun watchDbSource() = ties.apply {
//        value = ViewState(Status.LOADING, Source.DB)
//        val dbSource = loadFromDb()
//        addSource(dbSource, { ties ->
//            ties!!
//            removeSource(dbSource)
//            if (false) {
//                value = ViewState(Status.SUCCESS, Source.DB, ties)
//            } else {
//                watchNetworkSource()
//            }
//        })
//    }
//
//    private fun loadFromDb() = MutableLiveData<List<Tie>>().apply {
//        val query = tieBox.query().build()
//        RxQuery.observable(query)
//                // 模拟读取数据
////                .delay(2, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        { value = it },
//                        { com.orhanobut.logger.Logger.e(it, "") }
//                )
//    }
//
//    private fun watchNetworkSource() = ties.apply {
//        val networkSource = fetchFromNetwork()
//        addSource(networkSource, { resource ->
//            value = resource!!
//            if (!resource.isLoading()) {
//                removeSource(networkSource)
//            }
//        })
//    }
//
//    private fun fetchFromNetwork() = MutableLiveData<ViewState<List<Tie>>>().apply {
//        value = ViewState(Status.LOADING, Source.NETWORK)
//        tieApi.all()
////                // 模拟读取数据
////                .delay(3, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map { wrappers -> wrappers.map { TieWrapper.toTie(it) } }
//                .subscribe(
//                        {
//                            value = ViewState(Status.SUCCESS, Source.NETWORK, it)
//                            // 持久化
//                            tieBox.put(it)
//                        },
//                        { value = ViewState(Status.ERROR, Source.NETWORK, it) }
//                )
//    }
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

