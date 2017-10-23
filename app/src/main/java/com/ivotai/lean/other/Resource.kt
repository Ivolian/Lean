package com.ivotai.lean.other

class Resource<out T>(private val status: Status, val data: T?) {

    constructor(status: Status) : this(status, null)

    fun isLoading()  = this.status == Status.Loading
    fun isSuccess()  = this.status == Status.Success
    fun isError()  = this.status == Status.Error

}




