package com.ivotai.lean.user.di

import com.ivotai.lean.user.api.UserApi
import com.ivotai.lean.user.repo.UserRepo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class UserModule {

    @UserScope
    @Provides
    fun userApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @UserScope
    @Provides
    fun userRepo(api: UserApi): UserRepo = UserRepo(api)

//    @UserScope
//    @Provides
//    fun viewModelFactory(userRepo: UserRepo): UserViewModelFactory = UserViewModelFactory(userRepo)

}