package com.ivotai.lean.user.viewModel

import android.arch.lifecycle.MutableLiveData
import com.ivotai.lean.app.base.BaseViewModel
import com.ivotai.lean.user.api.UserApi
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class UserViewModel(private val userApi: UserApi) : BaseViewModel() {

    private val users = MutableLiveData<List<User>>()

    fun getUsers(): MutableLiveData<List<User>> {
        if (users.value == null) {
            userApi.all()
                    // 坑啊
                    .delay(3, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                users.value = it
                                Logger.d("getUsers")
                            },
                            {
                                Logger.e(it.toString())
                            }
                    )
                    .let { disposable.add(it) }
        }
        return users
    }

}
