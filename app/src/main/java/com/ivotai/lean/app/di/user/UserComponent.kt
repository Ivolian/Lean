package com.ivotai.lean.app.di.user

import com.ivotai.lean.tie.add.ui.AddTieAct
import com.ivotai.lean.tie.repo.TieRepo
import com.ivotai.lean.tie.ui.TieAct
import com.ivotai.lean.upload.repo.UploadRepo
import com.ivotai.lean.user.repo.UserRepo
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(UserModule::class))
interface UserComponent {

    fun inject(o: TieAct)
    fun inject(o: AddTieAct)

    fun getUploadRepo():UploadRepo
    fun getTieRepo():TieRepo
    fun getUserRepo(): UserRepo

}