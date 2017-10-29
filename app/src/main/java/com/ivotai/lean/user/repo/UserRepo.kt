package com.ivotai.lean.user.repo

import com.ivotai.lean.user.api.UserApi
import com.ivotai.lean.user.po.User
import io.objectbox.Box
import io.objectbox.rx.RxQuery
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class UserRepo @Inject constructor(private val userBox: Box<User>, private val userApi: UserApi) {

    fun getUsers(): Observable<List<User>> {
        val db = loadFromDb()
        val network = fetchFromNetwork()
        return network.concatWith(db).take(1)
    }

    private fun loadFromDb() = RxQuery
            .observable(userBox.query().build())
            .observeOn(AndroidSchedulers.mainThread())

    private fun fetchFromNetwork() = userApi.all()
            .delay(2, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { userBox.put(it) }
            .toObservable()

}