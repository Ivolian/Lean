package com.ivotai.lean.tie.ui

import com.ivotai.lean.tie.po.Tie

data class TieViewState(
     val loadingFirstPage: Boolean = false,       // Show the loading indicator instead of recyclerView
     val firstPageError: Throwable? = null,       // Show an error view if != null
     val data: List<Tie>? = null,                 // The items displayed in the recyclerview
     val loadingNextPage: Boolean = false,        // Shows the loading indicator for pagination
     val nextPageError: Throwable? = null,        // if != null, shows error toast that pagination failed
     val loadingPullToRefresh: Boolean = false,   // Shows the pull-to-refresh indicator
     val pullToRefreshError: Throwable? = null    // if != null, shows error toast that pull-to-refresh failed
)
