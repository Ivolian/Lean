package com.ivotai.lean.user.di

import com.ivotai.lean.user.viewModel.UserViewModelFactory
import dagger.Subcomponent

@UserScope
@Subcomponent(modules = arrayOf(UserModule::class))
interface UserComponent {

    fun getViewModelFactory(): UserViewModelFactory

}