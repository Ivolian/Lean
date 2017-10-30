package com.ivotai.lean.user.interactor

import com.ivotai.lean.base.ViewState1
import com.ivotai.lean.user.po.User
import com.ivotai.lean.user.repo.UserRepo
import io.reactivex.Observable
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepo: UserRepo) {

    fun loadUser(): Observable<ViewState1<List<User>>> = userRepo.fetchFromNetwork()
            .toObservable()
            .map { ViewState1(data = it) }
            .onErrorReturn { ViewState1(error = it) }
            .startWith(ViewState1())

}