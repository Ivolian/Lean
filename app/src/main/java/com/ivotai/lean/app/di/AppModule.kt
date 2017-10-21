package com.ivotai.lean.app.di

import android.content.Context
import com.ivotai.lean.app.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @AppScope
    @Provides
    fun context(): Context = app.applicationContext

}