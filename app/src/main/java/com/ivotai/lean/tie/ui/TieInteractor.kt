package com.ivotai.lean.tie.ui

import com.ivotai.lean.tie.repo.TieRepo
import io.reactivex.Observable
import javax.inject.Inject

class TieInteractor @Inject constructor(private val tieRepo: TieRepo) {

    private val firstPageNo = 0
    private var pageNo = 0
    fun loadingFirstPage(): Observable<TieViewState> = tieRepo.loadPage(firstPageNo)
            .map { TieViewState(loadingFirstPage = true, data = it) }
            .cast(TieViewState::class.java)
            .onErrorReturn { TieViewState(loadingFirstPage = true, firstPageError = it) }
            .startWith(TieViewState(loadingFirstPage = true))

    fun reload(): Observable<TieViewState> = tieRepo.loadPage(firstPageNo)
            .map { TieViewState(loadingPullToRefresh = true, data = it) }
            .cast(TieViewState::class.java)
            .onErrorReturn { TieViewState(loadingPullToRefresh = true, pullToRefreshError = it) }
            .startWith(TieViewState(loadingPullToRefresh = true))

    fun loadingNextPage(): Observable<TieViewState> = tieRepo.loadPage(++pageNo)
            .map { TieViewState(loadingNextPage = true, data = it) }
            .cast(TieViewState::class.java)
            .onErrorReturn { TieViewState(loadingNextPage = true, nextPageError = it) }
            .startWith(TieViewState(loadingNextPage = true))


}
