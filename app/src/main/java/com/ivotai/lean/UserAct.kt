package com.ivotai.lean

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.ivotai.lean.app.di.ComponentsHolder
import com.ivotai.lean.user.User
import com.ivotai.lean.user.viewModel.UserViewModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class UserAct : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val factory = ComponentsHolder.userComponent.getApi()
        userViewModel = ViewModelProviders.of(this, factory).get(UserViewModel::class.java)

var o =
        userViewModel.getUsers()

        o.subscribe(
                object : Observer<List<User>> {
                    override fun onError(e: Throwable) {
                        ""
                    }

                    override fun onNext(t: List<User>) {
                    ""
                    }

                    override fun onSubscribe(d: Disposable) {
                        ""
                    }

                    override fun onComplete() {
                        ""
                    }
                }
        )
    }

}

