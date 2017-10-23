package com.ivotai.lean.user.repo

import com.ivotai.lean.user.api.UserApi
import com.ivotai.lean.user.di.UserScope
import com.ivotai.lean.user.viewModel.User
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@UserScope
class UserRepo @Inject constructor(private val userApi: UserApi) {

    fun getUsers(): Single<List<User>> {
        return userApi.all()
                // 坑
                .delay(3, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}