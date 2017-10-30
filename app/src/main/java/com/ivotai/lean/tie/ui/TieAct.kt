package com.ivotai.lean.tie.ui

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.ivotai.lean.R
import com.ivotai.lean.app.di.ComponentsHolder
import com.ivotai.lean.tie.po.Tie
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.act_tie.*
import javax.inject.Inject


class TieAct : AppCompatActivity(), TieView {

    var ties = ArrayList<Tie>()

    override fun render(state: TieViewState) {
        when {
            state.loadingFirstPage -> {
                state.data?.let { tieAdapter.setNewData(it) }
            }
            state.loadingPullToRefresh -> {
                state.data?.let { tieAdapter.setNewData(it) }
                swipeRefreshLayout.isRefreshing = false
            }
            state.loadingNextPage -> {
                ties.addAll(state.data!!)
                tieAdapter.setNewData(ties)
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
        swipeRefreshLayout.setOnRefreshListener {
            tieInteractor.loadingFirstPage().subscribe{render(it)}
        }
        tieInteractor.loadingFirstPage().subscribe(object : Observer<TieViewState> {
            override fun onComplete() {
            }

            override fun onSubscribe(d: Disposable?) {
            }

            override fun onNext(t: TieViewState?) {
                render(t!!)
            }

            override fun onError(e: Throwable?) {
                ""
            }
        })

//        retryView.tvRetry.setOnClickListener {
//            tieInteractor.loadTies(0).subscribe { render(it) }
//        }
//        tieAdapter.setOnLoadMoreListener { tieInteractor.loadingNextPage() }
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