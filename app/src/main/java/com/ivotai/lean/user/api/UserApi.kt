package com.ivotai.lean.user.api

import com.ivotai.lean.user.User
import io.reactivex.Observable
import retrofit2.http.GET

interface UserApi {

    @GET("all")
    fun getAll(): Observable<List<User>>

}