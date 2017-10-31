package com.ivotai.lean.tie.ui

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.ToastUtils
import com.chad.library.adapter.base.loadmore.LoadMoreView
import com.ivotai.lean.R
import com.ivotai.lean.app.di.ComponentsHolder
import com.ivotai.lean.base.ViewState1
import com.ivotai.lean.tie.po.Tie
import com.ivotai.lean.tie.repo.TieRepo
import com.ivotai.lean.tie.useCase.LoadFirstPage
import com.ivotai.lean.tie.useCase.LoadNextPage
import com.ivotai.lean.tie.useCase.ReloadPage
import com.orhanobut.logger.Logger
import com.wang.avi.AVLoadingIndicatorView
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.act_tie.*
import javax.inject.Inject


class TieAct : AppCompatActivity(), TieView {

    override fun renderFirstPage(state: ViewState1<List<Tie>>) {
        Logger.d(state)
        when {
            state.isLoading() -> {
                loadingView.show()
                retryView.hide()
            }
            state.isError() -> {
                loadingView.hide()
                retryView.show()
            }
            state.isSuccess() -> {
                loadingView.hide()
                retryView.hide()
                tieAdapter.setNewData(state.data)
            }
        }
    }

    override fun renderNextPage(state: ViewState1<List<Tie>>) {
        when {
            state.isLoading() -> {
                // do nothing
            }
            state.isError() -> {
                tieAdapter.loadMoreComplete()
                ToastUtils.showShort(state.error?.message)
            }
            state.isSuccess() -> {
                tieAdapter.loadMoreComplete()
                tieAdapter.addData(state.data!!)
                tieAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun reloadPage(state: ViewState1<List<Tie>>) {
        when {
            state.isLoading() -> {
                swipeRefreshLayout.isRefreshing = true
            }
            state.isError() -> {
                swipeRefreshLayout.isRefreshing = false
                ToastUtils.showShort(state.error?.message)
            }
            state.isSuccess() -> {
                swipeRefreshLayout.isRefreshing = false
                tieAdapter.setNewData(state.data)
            }
        }
    }

    @Inject
    lateinit var tieRepo: TieRepo

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

        // intent => use case
        retryView.tvRetry.setOnClickListener { LoadFirstPage(this, tieRepo) }
        swipeRefreshLayout.setOnRefreshListener { ReloadPage(this, tieRepo) }
        tieAdapter.setOnLoadMoreListener({ LoadNextPage(this, tieRepo, pageNo) }, recyclerView)
        LoadFirstPage(this, tieRepo)
    }

    private val pageNo
        get() = tieAdapter.data.size / pageSize

    private val pageSize = 10

    private val tieAdapter = TieAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@TieAct)
            tieAdapter.bindToRecyclerView(this)
//            tieAdapter.setOnItemClickListener { _, _, _ -> }
            var lv = AVLoadingIndicatorView(this@TieAct)

            tieAdapter.setLoadMoreView(object : LoadMoreView() {
                override fun getLayoutId() = R.layout.loadmore_view

                override fun getLoadingViewId()= R.id.load_more_loading_view

                override fun getLoadEndViewId() = 0

                override fun getLoadFailViewId() = R.id.load_more_load_fail_view
            })
            addItemDecoration(
                    HorizontalDividerItemDecoration.Builder(this@TieAct)
                            .colorResId(R.color.material_grey_100)
                            .size(ConvertUtils.dp2px(8f))
                            .build())
        }
    }

}