package com.ivotai.lean.user.repo

import com.ivotai.lean.user.api.UserApi
import com.ivotai.lean.user.po.User
import io.objectbox.Box
import io.objectbox.rx.RxQuery
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class UserRepo(private val userBox: Box<User>, private val userApi: UserApi) {

    fun getUsers(): Observable<List<User>> {
        val db = loadFromDb()
        val network = fetchFromNetwork()
//      return  Observable.concat(db,network).first(null).toObservable()
        return network.concatWith(db).take(1)
    }

    private fun loadFromDb(): Observable<List<User>> {
        return RxQuery.observable(userBox.query().build())
                .observeOn(AndroidSchedulers.mainThread())

    }

    private fun fetchFromNetwork(): Observable<List<User>> {
        return userApi.all()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext({
                    userBox.put(it)
                })
    }

}