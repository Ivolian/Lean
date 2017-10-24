package com.ivotai.lean.other

class Resource<out T>(private val status: Status, val message: String, val data: T?) {

    constructor(status: Status, message: String) : this(status, message, null)

    fun isLoading() = this.status == Status.Loading
    fun isSuccess() = this.status == Status.Success
    fun isError() = this.status == Status.Error
}




