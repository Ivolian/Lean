package com.ivotai.lean.app.di

import android.content.Context
import com.ivotai.lean.BuildConfig
import com.ivotai.lean.app.App
import com.ivotai.lean.tie.MyObjectBox
import dagger.Module
import dagger.Provides
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser

@Module
class AppModule(val app: App) {

    @AppScope
    @Provides
    fun context(): Context = app.applicationContext

    @AppScope
    @Provides
    fun boxStore(): BoxStore {
        val boxStore = MyObjectBox.builder().androidContext(app).build()
        if (BuildConfig.DEBUG) {
            AndroidObjectBrowser(boxStore).start(app)
        }
        return boxStore
    }

}