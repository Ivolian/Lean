package com.ivotai.lean.user.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.azoft.carousellayoutmanager.CenterScrollListener
import com.ivotai.lean.R
import com.ivotai.lean.app.Current
import com.ivotai.lean.tie.ui.TieAct
import com.ivotai.lean.user.useCase.LoadUserUseCase
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_user.*
import java.util.concurrent.TimeUnit

class UserAct : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_user)

        // system ui
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        // ui
        initRecyclerView()
        lifecycle.addObserver(loadingView)

        // intent => use case
        RxView.clicks(retryView.tvRetry).debounce(500, TimeUnit.SECONDS).subscribe { loadUser() }
        loadUser()
    }

    private fun loadUser() {
        LoadUserUseCase(Consumer {
            when {
                it.isLoading() -> {
                    loadingView.show()
                    retryView.hide()
                }
                it.isError() -> {
                    loadingView.hide()
                    retryView.show()
                }
                it.isSuccess() -> {
                    loadingView.hide()
                    retryView.hide()
                    userAdapter.setNewData(it.data)
                }
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
            userAdapter.setOnItemClickListener { _, _, pos ->
                Current.user = userAdapter.getItem(pos)
                startActivity(Intent(this@UserAct, TieAct::class.java))
            }
        }
    }

}

