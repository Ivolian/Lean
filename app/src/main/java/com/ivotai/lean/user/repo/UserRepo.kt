package com.ivotai.lean.user.repo

import com.ivotai.lean.user.api.UserApi
import com.ivotai.lean.user.po.User
import io.objectbox.Box
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserRepo @Inject constructor(private val userBox: Box<User>, private val userApi: UserApi) {

//     fun loadFromDb(): Observable<List<User>> = RxQuery
//            .observable(userBox.query().build())
//            .observeOn(AndroidSchedulers.mainThread())

     fun fetchFromNetwork(): Single<List<User>> = userApi.all()
//            .delay(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { userBox.put(it) }

}