package com.ivotai.lean.tie.viewModel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.ivotai.lean.tie.repo.TieRepo
import com.ivotai.lean.user.di.UserScope
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
@UserScope
class TieViewModelFactory @Inject constructor(private val tieRepo: TieRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(TieViewModel::class.java)) {
            return TieViewModel(tieRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}