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
import com.ivotai.lean.app.di.ComponentsHolder
import com.ivotai.lean.tie.ui.TieAct
import kotlinx.android.synthetic.main.act_user.*
import javax.inject.Inject

class UserAct : AppCompatActivity(), UserView {

    @Inject lateinit var userInteractor: UserInteractor

    override fun render(state: UserViewState) {
        when (state) {
            is UserViewState.LoadingState -> {
                loadingView.show()
                retryView.hide()
            }
            is UserViewState.ErrorState -> {
                loadingView.hide()
                retryView.show()
            }
            is UserViewState.DataState -> {
                loadingView.hide()
                retryView.hide()
                userAdapter.setNewData(state.users)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_user)

        // di
        ComponentsHolder.userComponent.inject(this)

        // system ui
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        // ui
        initRecyclerView()
        lifecycle.addObserver(loadingView)

        // intent useCase interactor
//        RxView.clicks(tvRetry).debounce(500, TimeUnit.SECONDS)
//                .subscribe { userInteractor.loadUser().subscribe { render(it) } }
        userInteractor.loadUser().subscribe { render(it) }
    }

    private var userAdapter = UserAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = CarouselLayoutManager(CarouselLayoutManager.VERTICAL)
                    .apply { setPostLayoutListener(CarouselZoomPostLayoutListener()) }
            addOnScrollListener(CenterScrollListener())
            userAdapter.bindToRecyclerView(this)
            userAdapter.setOnItemClickListener { _, _, _ ->
                startActivity(Intent(this@UserAct, TieAct::class.java))
            }
        }
    }

}

