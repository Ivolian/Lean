package com.ivotai.lean.user.di

import com.ivotai.lean.user.api.UserApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class UserModule {

    @UserScope
    @Provides
    fun api(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

}