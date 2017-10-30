package com.ivotai.lean.tie.ui

import com.ivotai.lean.base.ViewState1
import com.ivotai.lean.tie.po.Tie

interface TieView {

    fun renderFirstPage(state: ViewState1<List<Tie>>)
    fun renderNextPage(state: ViewState1<List<Tie>>)
    fun reloadPage(state: ViewState1<List<Tie>>)

}