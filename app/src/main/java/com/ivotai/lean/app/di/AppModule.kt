package com.ivotai.lean.app.di

import android.content.Context
import com.ivotai.lean.app.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    @Singleton
    @Provides
    fun context(): Context = app.applicationContext

}