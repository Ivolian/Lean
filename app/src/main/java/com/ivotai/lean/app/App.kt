package com.ivotai.lean.app

import android.app.Application
import com.blankj.utilcode.util.Utils
import com.ivotai.lean.app.di.ComponentsHolder
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.squareup.leakcanary.LeakCanary

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        ComponentsHolder.init(this)

        Utils.init(this)
        Logger.addLogAdapter(AndroidLogAdapter())
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return
//        }
        LeakCanary.install(this)
    }

}