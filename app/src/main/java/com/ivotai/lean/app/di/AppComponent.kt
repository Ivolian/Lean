package com.ivotai.lean.app.di

import dagger.Component

@AppScope
@Component(modules = arrayOf(AppModule::class, NetworkModule::class))
interface AppComponent {

}