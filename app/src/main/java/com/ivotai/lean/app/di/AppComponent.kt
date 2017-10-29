package com.ivotai.lean.app.di

import com.ivotai.lean.di.UserComponent
import dagger.Component

@AppScope
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {

    fun getUserComponent(): UserComponent

}