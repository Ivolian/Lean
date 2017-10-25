package com.ivotai.lean.user.repo

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import com.ivotai.lean.app.base.Resource
import com.ivotai.lean.app.base.Source
import com.ivotai.lean.app.base.Status
import com.ivotai.lean.user.api.UserApi
import com.ivotai.lean.user.viewModel.User
import io.objectbox.Box
import io.objectbox.rx.RxQuery
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class UserRepo(private val userBox: Box<User>, private val userApi: UserApi) {

    private val users = MediatorLiveData<Resource<List<User>>>()

    fun loadUsers() = users.apply {
//        userBox.removeAll()
        if (value != null) {
            return@apply
        }
        watchDbSource()
    }

    private fun watchDbSource() = users.apply{
        value = Resource(Status.LOADING, Source.DB)
        val dbSource = loadFromDb()
        addSource(dbSource, { users ->
            users!!
            removeSource(dbSource)
            if (!users.isEmpty()) {
                value = Resource(Status.SUCCESS, Source.DB, users)
            } else {
                watchNetworkSource()
            }
        })
    }

    private fun loadFromDb() = MutableLiveData<List<User>>().apply {
        val query = userBox.query().build()
        RxQuery.observable(query)
                // 模拟读取数据
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { value = it },
                        { com.orhanobut.logger.Logger.e(it, "") }
                )
    }

    private fun watchNetworkSource() = users.apply {
        val networkSource = fetchFromNetwork()
        addSource(networkSource, { resource ->
            value = resource!!
            if (!resource.isLoading()) {
                removeSource(networkSource)
            }
        })
    }

    private fun fetchFromNetwork() = MutableLiveData<Resource<List<User>>>().apply {
        value = Resource(Status.LOADING, Source.NETWORK)
        userApi.all()
                // 模拟读取数据
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            value = Resource(Status.SUCCESS, Source.NETWORK, it)
                            // 持久化
                            userBox.put(it)
                        },
                        { value = Resource(Status.ERROR, Source.NETWORK, it) }
                )
    }

}