package com.ivotai.lean.tie.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.ivotai.lean.R
import com.ivotai.lean.app.di.ComponentsHolder
import com.ivotai.lean.tie.viewModel.TieViewModel
import com.ivotai.lean.tie.viewModel.TieViewModelFactory
import kotlinx.android.synthetic.main.act_tie.*
import javax.inject.Inject


class TieAct : AppCompatActivity() {

    @Inject
    lateinit var factory: TieViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_tie)

        // 白底黑字
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        // ViewModel
        ComponentsHolder.userComponent.inject(this)
        val tieViewModel = ViewModelProviders.of(this, factory)
                .get(TieViewModel::class.java)

        // init view
        initRecyclerView()
        lifecycle.addObserver(loadingView)
        retryView.tvRetry.setOnClickListener {
//            tieViewModel.getTies()
        }
//
//        tieViewModel.getTies().observe(this, android.arch.lifecycle.Observer { resources ->
//            resources!!
//            Logger.d(resources)
//            if (resources.isLoading()) {
//                loadingView.show()
//                retryView.hide()
//            }
//            if (resources.isError()) {
//                loadingView.hide()
//                retryView.show()
//            }
//            if (resources.isSuccess()) {
//                loadingView.hide()
//                retryView.hide()
//                tieAdapter.setNewData(resources.data)
//            }
//
//        })

    }

    private var tieAdapter = TieAdapter()

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@TieAct)
            tieAdapter.bindToRecyclerView(this)
            tieAdapter.setOnItemClickListener { _, _, _ -> }
        }
    }

}