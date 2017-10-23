package com.ivotai.lean.user.repo

import android.arch.lifecycle.MediatorLiveData
import com.ivotai.lean.other.Resource
import com.ivotai.lean.other.Status
import com.ivotai.lean.user.api.UserApi
import com.ivotai.lean.user.viewModel.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class UserRepo(private val userApi: UserApi) {

    val usersResource = MediatorLiveData<Resource<List<User>>>()
        get() = field.apply {
            if (value == null) {
                value = Resource(Status.Loading)
                userApi.all()
                        // 坑
                        .delay(3, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { value = Resource(Status.Success, it) },
                                { value = Resource(Status.Error) }
                        )
            }
        }

}