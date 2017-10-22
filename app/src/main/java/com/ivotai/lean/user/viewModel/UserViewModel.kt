package com.ivotai.lean.user.viewModel

import android.arch.lifecycle.ViewModel
import com.ivotai.lean.user.User
import com.ivotai.lean.user.api.UserApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class UserViewModel(private val userApi: UserApi) : ViewModel() {

    fun getUsers():Observable<List<User>>{
        return userApi.getAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }

}
