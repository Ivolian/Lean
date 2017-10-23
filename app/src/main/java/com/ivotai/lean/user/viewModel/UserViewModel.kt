package com.ivotai.lean.user.viewModel

import android.arch.lifecycle.MutableLiveData
import com.ivotai.lean.app.base.BaseViewModel
import com.ivotai.lean.user.api.UserApi
import com.kakai.android.autoviewmodelfactory.annotations.AutoViewModelFactory
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

@AutoViewModelFactory
class UserViewModel(private val userApi: UserApi) : BaseViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val users: MutableLiveData<List<User>> = MutableLiveData()
        get() {
            if (field.value == null) {
                isLoading.value = true
                userApi.all()
                        // 坑啊
                        .delay(3, TimeUnit.SECONDS)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    users.value = it
                                    Logger.d("getUsers")
                                    isLoading.value = false
                                },
                                {
                                    Logger.e(it.toString())
                                    isLoading.value = false
                                }
                        )
                        .let { disposable.add(it) }
            }
            return field
        }

}
