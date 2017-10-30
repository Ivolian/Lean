package com.ivotai.lean.user.api

import com.ivotai.lean.user.po.User
import io.reactivex.Single
import retrofit2.http.GET

interface UserApi {
    @GET("user/all")
    fun all(): Single<List<User>>
}