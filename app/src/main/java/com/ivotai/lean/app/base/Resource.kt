package com.ivotai.lean.app.base

data class Resource<out T>(private val status: Status, val source: Source, val data: T?, val error: Throwable?) {

    constructor(status: Status, source: Source)
            : this(status, source, data = null)

    constructor(status: Status, source: Source, data: T?)
            : this(status, source, data, null)

    constructor(status: Status, source: Source, error: Throwable?)
            : this(status, source, null, error)

    fun isLoading() = this.status == Status.LOADING
    fun isSuccess() = this.status == Status.SUCCESS
    fun isError() = this.status == Status.ERROR

}




