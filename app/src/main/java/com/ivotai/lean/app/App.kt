package com.ivotai.lean.app

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.ivotai.lean.app.di.ComponentsHolder
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        ComponentsHolder.init(this)

        Utils.init(this)
        Logger.addLogAdapter(AndroidLogAdapter())
    }

}