package com.ivotai.lean.tie.ui

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.ivotai.lean.R
import com.ivotai.lean.app.di.ComponentsHolder
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.act_tie.*
import javax.inject.Inject


class TieAct : AppCompatActivity(), TieView {

    override fun render(state: TieViewState) {
        when (state) {
            is TieViewState.LoadingState -> {
                loadingView.show()
                retryView.hide()
            }
            is TieViewState.ErrorState -> {
                loadingView.hide()
                retryView.show()
            }
            is TieViewState.DataState -> {
                loadingView.hide()
                retryView.hide()
                tieAdapter.setNewData(state.ties)
            }
        }
    }

    @Inject
    lateinit var tieInteractor: TieInteractor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_tie)

        // di
        ComponentsHolder.userComponent.inject(this)

        // system ui
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        // ui
        initRecyclerView()
        lifecycle.addObserver(loadingView)

        // intent
        Observable.merge(
                Observable.just(true), RxView.clicks(retryView.tvRetry))
                .subscribe {
                    tieInteractor.loadTies(0).subscribe { render(it) }
                }
//        tieInteractor.loadTies(0).subscribe { render(it) }
//        retryView.tvRetry.setOnClickListener {
//            tieInteractor.loadTies(0).subscribe { render(it) }
//        }
//        tieAdapter.setOnLoadMoreListener{ tieInteractor.loadTies(0)}
    }

    private var tieAdapter = TieAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@TieAct)
            tieAdapter.bindToRecyclerView(this)
//            tieAdapter.setOnItemClickListener { _, _, _ -> }
        }
    }

}