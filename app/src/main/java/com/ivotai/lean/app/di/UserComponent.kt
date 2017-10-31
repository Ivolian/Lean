package com.ivotai.lean.app.di

import com.ivotai.lean.tie.add.AddTieAct
import com.ivotai.lean.tie.repo.TieRepo
import com.ivotai.lean.tie.ui.TieAct
import com.ivotai.lean.upload.api.UploadApi
import com.ivotai.lean.user.ui.UserAct
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(UserModule::class))
interface UserComponent {

    fun inject(o: UserAct)
    fun inject(o: TieAct)
    fun inject(o: AddTieAct)

    fun getUploadApi():UploadApi
    fun getTieRepo():TieRepo

}