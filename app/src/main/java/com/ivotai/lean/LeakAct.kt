package com.ivotai.lean

import android.os.AsyncTask
import android.os.Bundle
import android.os.SystemClock
import android.support.v7.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


/**
 * Created by thinkpad on 2017/10/24.
 */
class LeakAct:AppCompatActivity (){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
setContentView(R.layout.act_user)
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Long> {
                    override fun onNext(t: Long) {
                    com.orhanobut.logger.Logger.e("...")
                    }

                    override fun onError(e: Throwable) {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onComplete() {
                    }
                })
    startAsyncTask()
    }


    fun startAsyncTask() {
        object :AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                SystemClock.sleep(20000)
            }
        }.execute()
    }
}