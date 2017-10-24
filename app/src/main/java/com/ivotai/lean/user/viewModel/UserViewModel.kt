package com.ivotai.lean.user.viewModel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.ivotai.lean.other.Resource
import com.ivotai.lean.user.repo.UserRepo
import com.kakai.android.autoviewmodelfactory.annotations.AutoViewModelFactory

@AutoViewModelFactory
class UserViewModel(private val userRepo: UserRepo) : ViewModel() {

    fun getUsers(): MutableLiveData<Resource<List<User>>> {
        return userRepo.network
    }

}
