package com.ivotai.lean.user.di

import com.ivotai.lean.user.api.UserApi
import dagger.Subcomponent

@UserScope
@Subcomponent(modules = arrayOf(UserModule::class))
interface UserComponent {

    fun getApi(): UserApi

}