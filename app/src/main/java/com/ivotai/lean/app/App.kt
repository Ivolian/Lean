package com.ivotai.lean.app

import android.app.Application
import com.ivotai.lean.app.di.ComponentsHolder


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        ComponentsHolder.init(this)
    }

}