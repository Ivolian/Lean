package com.ivotai.lean.di

import com.ivotai.lean.tie.api.TieApi
import com.ivotai.lean.tie.po.Tie
import com.ivotai.lean.user.api.UserApi
import com.ivotai.lean.user.po.User
import dagger.Module
import dagger.Provides
import io.objectbox.Box
import io.objectbox.BoxStore
import retrofit2.Retrofit

@Module
class UserModule {

    @Provides
    fun userApi(retrofit: Retrofit): UserApi = retrofit.create(UserApi::class.java)

    @Provides
    fun userBox(boxStore: BoxStore): Box<User> = boxStore.boxFor(User::class.java)

    @Provides
    fun tieApi(retrofit: Retrofit): TieApi = retrofit.create(TieApi::class.java)

    @Provides
    fun tieBox(boxStore: BoxStore): Box<Tie> = boxStore.boxFor(Tie::class.java)

}