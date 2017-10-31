package com.ivotai.lean.user.ui

import com.ivotai.lean.app.base.ViewState1
import com.ivotai.lean.user.po.User

interface UserView {

    fun render(state: ViewState1<List<User>>)

}
