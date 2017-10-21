package com.ivotai.lean

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ivotai.lean.app.di.ComponentsHolder
import com.ivotai.lean.user.User
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class UserAct : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val api = ComponentsHolder.userComponent.getApi()
        api.getAll().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                      object:  Observer<List<User>> {
                          override fun onError(e: Throwable) {
           ""               }

                          override fun onNext(t: List<User>) {
                             var user = t[0]
                          }

                          override fun onSubscribe(d: Disposable) {
          ""                }

                          override fun onComplete() {
        ""
                          }
                      }
                )
    }

}

