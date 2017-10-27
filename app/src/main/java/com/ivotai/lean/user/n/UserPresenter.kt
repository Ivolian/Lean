/*
 * Copyright 2016 Hannes Dorfmann.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.ivotai.lean.user.n

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import com.ivotai.lean.user.repo.UserRepo

class UserPresenter(private val userRepo: UserRepo) : MviBasePresenter<UserView, UserViewState>() {

    override fun bindIntents() {

        val loadIntent = intent(UserView::loadIntents)
        val retryIntent =
                loadIntent.concatWith(        intent(UserView::retryIntents))
                .flatMap { userRepo.getUsers() }
                .map { UserViewState.DataState(it) }
                .cast(UserViewState::class.java)
                .startWith(UserViewState.LoadingState())
                .onErrorReturn { UserViewState.ErrorState(it) }
        subscribeViewState(retryIntent, UserView::render)


    }
}
