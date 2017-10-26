package com.ivotai.lean.tie.viewModel

import android.arch.lifecycle.ViewModel
import com.ivotai.lean.tie.repo.TieRepo

class TieViewModel(private val tieRepo: TieRepo) : ViewModel() {

    fun getTies() = tieRepo.loadTies()

}