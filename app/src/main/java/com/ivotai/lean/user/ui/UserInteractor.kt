package com.ivotai.lean.user.ui

import com.ivotai.lean.user.repo.UserRepo
import io.reactivex.Observable
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepo: UserRepo) {

    fun loadUser(): Observable<UserViewState> = userRepo.fetchFromNetwork()
            .map { UserViewState.DataState(it) }
            .cast(UserViewState::class.java)
            .onErrorReturn { UserViewState.ErrorState(it) }
            .startWith(UserViewState.LoadingState())

}