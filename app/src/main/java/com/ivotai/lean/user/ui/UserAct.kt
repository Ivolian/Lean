package com.ivotai.lean.user.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.azoft.carousellayoutmanager.CenterScrollListener
import com.ivotai.lean.R
import com.ivotai.lean.app.di.ComponentsHolder
import com.ivotai.lean.tie.repo.TieRepo
import com.ivotai.lean.user.viewModel.User
import com.ivotai.lean.user.viewModel.UserViewModel
import com.ivotai.lean.user.viewModel.UserViewModelFactory
import com.orhanobut.logger.Logger
import io.objectbox.Box
import kotlinx.android.synthetic.main.act_user.*
import kotlinx.android.synthetic.main.retry_view.*
import javax.inject.Inject

class UserAct : AppCompatActivity() {

    @Inject
    lateinit var factory: UserViewModelFactory

    @Inject
    lateinit var tieRepo:TieRepo

    @Inject
    lateinit var userBox: Box<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_user)

        // 白底黑字
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        // getViewModel
        ComponentsHolder.userComponent.inject(this)
        val userViewModel = ViewModelProviders.of(this, factory)
                .get(UserViewModel::class.java)

        // init view
        initRecyclerView()
        lifecycle.addObserver(loadingView)
        tvRetry.setOnClickListener { userViewModel.getUsers() }

        userViewModel.getUsers().observe(this, android.arch.lifecycle.Observer { resources ->
            resources!!
            Logger.d(resources)
            if (resources.isLoading()) {
                loadingView.show()
                retryView.hide()
            }
            if (resources.isError()) {
                loadingView.hide()
                retryView.show()
            }
            if (resources.isSuccess()) {
                loadingView.hide()
                retryView.hide()
                userAdapter.setNewData(resources.data)
            }

        })

    }

    private var userAdapter = UserAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = CarouselLayoutManager(CarouselLayoutManager.VERTICAL)
                    .apply { setPostLayoutListener(CarouselZoomPostLayoutListener()) }
            addOnScrollListener(CenterScrollListener())
            userAdapter.bindToRecyclerView(this)
            userAdapter.setOnItemClickListener { _, _, _ ->
            }
        }
    }

}

