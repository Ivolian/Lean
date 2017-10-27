package com.ivotai.lean.user.api

import com.ivotai.lean.user.po.User
import io.reactivex.Observable
import retrofit2.http.GET

interface UserApi {

    @GET("user/all")
    fun all(): Observable<List<User>>

}