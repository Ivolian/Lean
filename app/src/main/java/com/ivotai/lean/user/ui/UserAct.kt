package com.ivotai.lean.user.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.azoft.carousellayoutmanager.CenterScrollListener
import com.blankj.utilcode.util.ToastUtils
import com.ivotai.lean.R
import com.ivotai.lean.app.di.ComponentsHolder
import com.ivotai.lean.user.viewModel.UserViewModel
import kotlinx.android.synthetic.main.act_user.*

class UserAct : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_user)

        // 白底黑字
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        // getViewModel
        userViewModel = ViewModelProviders
                .of(this, ComponentsHolder.userComponent.getViewModelFactory())
                .get(UserViewModel::class.java)


        // init view
        initRecyclerView()

        loadingView.startAnim(lifecycle)
        userViewModel.getUsers().observe(this, Observer {
            userAdapter.setNewData(it)
            loadingView.finishAnim()
        })

        userAdapter.setOnItemClickListener { _, _, _ -> ToastUtils.showLong("ss") }
    }


    private var userAdapter = UserAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = CarouselLayoutManager(CarouselLayoutManager.VERTICAL)
                    .apply { setPostLayoutListener(CarouselZoomPostLayoutListener()) }
            addOnScrollListener(CenterScrollListener())
            userAdapter.bindToRecyclerView(this)
        }
    }

}

