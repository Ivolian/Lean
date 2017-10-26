package com.ivotai.lean.user.di

import com.ivotai.lean.tie.ui.TieAct
import com.ivotai.lean.user.ui.UserAct
import dagger.Subcomponent

@UserScope
@Subcomponent(modules = arrayOf(UserModule::class))
interface UserComponent {

    fun inject(o: UserAct)
    fun inject(o: TieAct)

}