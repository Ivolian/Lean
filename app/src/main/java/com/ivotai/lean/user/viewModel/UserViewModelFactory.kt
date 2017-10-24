package com.ivotai.lean.user.viewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ivotai.lean.user.di.UserScope

import com.ivotai.lean.user.repo.UserRepo

import javax.inject.Inject

@UserScope
class UserViewModelFactory @Inject constructor(private val userRepo: UserRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(userRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
