package com.ivotai.lean.user.repo

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import com.ivotai.lean.app.base.Resource
import com.ivotai.lean.app.base.Status
import com.ivotai.lean.user.api.UserApi
import com.ivotai.lean.user.viewModel.User
import io.objectbox.Box
import io.objectbox.rx.RxQuery
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class UserRepo(private val userBox: Box<User>, private val userApi: UserApi) {

    val users = MediatorLiveData<Resource<List<User>>>()


    fun load() = users.apply {
        if (value != null) {
            return@apply
        }

        value = Resource(Status.Loading, "loading from db")
        val dbSource = loadFromDb()
        addSource(dbSource, { users ->
            users!!
            removeSource(dbSource)
            if (!users.isEmpty()) {
                value = Resource(Status.Success, "load finish from db", users)
            } else {
                addNetworkSource()
            }
        })
    }

    private fun addNetworkSource() = users.apply {
        val networkSource = fetchFromNetwork()
        addSource(networkSource, { resource ->
            value = resource!!
            if (!resource.isLoading()) {
                removeSource(networkSource)
            }
        })
    }

    private fun loadFromDb() = MutableLiveData<List<User>>().apply {
        val query = userBox.query().build()
        RxQuery.observable(query)
                .delay(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { value = it },
                        { com.orhanobut.logger.Logger.e(it, "") }
                )
    }

    private fun fetchFromNetwork() = MutableLiveData<Resource<List<User>>>().apply {
        value = Resource(Status.Loading, "loading from network")
        userApi.all()
                // 模拟读取数据
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            value = Resource(Status.Success, "network", it)
                            userBox.put(it)
                        },
                        { value = Resource(Status.Error, it.message ?: "network") }
                )
    }

}