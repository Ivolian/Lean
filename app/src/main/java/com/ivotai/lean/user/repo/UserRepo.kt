package com.ivotai.lean.user.repo

import com.ivotai.lean.user.api.UserApi
import com.ivotai.lean.user.po.User
import io.objectbox.Box
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserRepo @Inject constructor(private val userBox: Box<User>, private val userApi: UserApi) {

    fun fetchFromNetwork(): Single<List<User>> = userApi.all()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            // 持久化
            .doOnSuccess { userBox.put(it) }

}