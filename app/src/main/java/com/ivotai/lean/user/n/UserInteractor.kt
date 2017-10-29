package com.ivotai.lean.user.n

import com.ivotai.lean.user.repo.UserRepo
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepo: UserRepo) {

    fun loadUser() = userRepo.getUsers()
            .map { UserViewState.DataState(it) }
            .cast(UserViewState::class.java)
            .onErrorReturn { UserViewState.ErrorState(it) }
            .startWith(UserViewState.LoadingState())

}