package com.ivotai.lean.tie.ui

import com.ivotai.lean.tie.repo.TieRepo
import io.reactivex.Observable
import javax.inject.Inject

class TieInteractor@Inject constructor(private val tieRepo: TieRepo) {

    fun loadTies(): Observable<TieViewState> = tieRepo.getTies()
            .map { TieViewState.DataState(it) }
            .cast(TieViewState::class.java)
            .onErrorReturn { TieViewState.ErrorState(it) }
            .startWith(TieViewState.LoadingState())

}