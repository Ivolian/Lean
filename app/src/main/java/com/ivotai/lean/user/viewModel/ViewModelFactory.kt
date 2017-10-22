package com.ivotai.lean.user.viewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ivotai.lean.user.api.UserApi

class ViewModelFactory(private val userApi: UserApi) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}