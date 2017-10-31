package com.ivotai.lean.app.base

data class ViewState1<out T>(
        val data: T? = null,
        val error: Throwable? = null
) {

    fun isLoading() = data == null && error == null
    fun isSuccess() = data != null
    fun isError() = error != null

}
