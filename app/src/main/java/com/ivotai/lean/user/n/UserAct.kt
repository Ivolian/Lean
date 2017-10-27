package com.ivotai.lean.user.n

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import com.azoft.carousellayoutmanager.CarouselLayoutManager
import com.azoft.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.azoft.carousellayoutmanager.CenterScrollListener
import com.hannesdorfmann.mosby3.mvi.MviActivity
import com.ivotai.lean.R
import com.ivotai.lean.app.di.ComponentsHolder
import com.ivotai.lean.tie.ui.TieAct
import com.ivotai.lean.user.repo.UserRepo
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.act_user.*
import kotlinx.android.synthetic.main.retry_view.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UserAct : MviActivity<UserView, UserPresenter>(), UserView {
    @Inject lateinit var userApi: UserRepo

    override fun createPresenter(): UserPresenter {
        ComponentsHolder.userComponent.inject(this)
        return UserPresenter(userApi)
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadIntents(): Observable<Boolean> {
        return Observable.just(true)

    }

    override fun retryIntents(): Observable<Boolean> {
        return RxView.clicks(retryView.tvRetry)
                .debounce(500, TimeUnit.MILLISECONDS)
                .map { true }
    }

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

        // 白底黑字
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        // init view
        initRecyclerView()
        lifecycle.addObserver(loadingView)
        tvRetry.setOnClickListener { loadIntents() }
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

