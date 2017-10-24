package com.ivotai.lean.user.di

import com.ivotai.lean.user.api.UserApi
import com.ivotai.lean.user.repo.UserRepo
import com.ivotai.lean.user.viewModel.User
import dagger.Module
import dagger.Provides
import io.objectbox.Box
import io.objectbox.BoxStore
import retrofit2.Retrofit

@Module
class UserModule {

    @UserScope
    @Provides
    fun userApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @UserScope
    @Provides
    fun userBox(boxStore: BoxStore): Box<User> = boxStore.boxFor(User::class.java)

    @UserScope
    @Provides
    fun userRepo(api: UserApi,box:Box<User>): UserRepo = UserRepo(box,api)

//    @UserScope
//    @Provides
//    fun viewModelFactory(userRepo: UserRepo): UserViewModelFactory = UserViewModelFactory(userRepo)


}