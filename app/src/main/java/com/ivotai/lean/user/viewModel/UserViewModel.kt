package com.ivotai.lean.user.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ivotai.lean.app.base.Resource
import com.ivotai.lean.user.repo.UserRepo

class UserViewModel(private val userRepo: UserRepo) : ViewModel() {

    fun getUsers(): MutableLiveData<Resource<List<User>>> {
        userRepo.load()
        return userRepo.users
    }

}
