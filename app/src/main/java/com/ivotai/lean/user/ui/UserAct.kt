package com.ivotai.lean.user.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.ivotai.lean.R
import com.ivotai.lean.app.di.ComponentsHolder
import com.ivotai.lean.user.viewModel.User
import com.ivotai.lean.user.viewModel.UserViewModel
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.act_user.*

class UserAct : AppCompatActivity() {

    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_user)
        val factory = ComponentsHolder.userComponent.getApi()
        userViewModel = ViewModelProviders.of(this, factory).get(UserViewModel::class.java)

        rvUsers.layoutManager = LinearLayoutManager(this)
        val adapter = UserAdapter()
        adapter.bindToRecyclerView(rvUsers)


        var o =
                userViewModel.getUsers()

        o.subscribe(
                object : Observer<List<User>> {
                    override fun onError(e: Throwable) {
                        ""
                    }

                    override fun onNext(t: List<User>) {
                        adapter.setNewData(t)
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

