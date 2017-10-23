package com.ivotai.lean.user.viewModel

import android.arch.lifecycle.MutableLiveData
import com.ivotai.lean.app.base.BaseViewModel
import com.ivotai.lean.user.repo.UserRepo
import com.kakai.android.autoviewmodelfactory.annotations.AutoViewModelFactory
import com.orhanobut.logger.Logger

@AutoViewModelFactory
class UserViewModel(private val userRepo: UserRepo) : BaseViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val users: MutableLiveData<List<User>> = MutableLiveData()
        get() {
            if (field.value == null) {
                isLoading.value = true
                userRepo.getUsers()
                        .subscribe(
                                {
                                    field.value = it
                                    isLoading.value = false
                                },
                                {
                                    Logger.e(it.toString())
                                    isLoading.value = false
                                }
                        ).let { disposable.add(it) }
            }
            return field
        }


}
