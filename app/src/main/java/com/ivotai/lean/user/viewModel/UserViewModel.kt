package com.ivotai.lean.user.viewModel

import android.arch.lifecycle.ViewModel
import com.ivotai.lean.user.repo.UserRepo

class UserViewModel(private val userRepo: UserRepo) : ViewModel() {

    fun getUsers() = userRepo.loadUsers()

}
