package com.ivotai.lean.app.di

import com.ivotai.lean.app.App

object ComponentsHolder {

    private lateinit var app: App

    fun init(app: App) {
        ComponentsHolder.app = app
    }

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .build()
    }

    val userComponent by lazy { appComponent.getUserComponent() }

}