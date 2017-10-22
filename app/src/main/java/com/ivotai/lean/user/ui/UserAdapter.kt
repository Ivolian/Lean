package com.ivotai.lean.user.ui

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ivotai.lean.R
import com.ivotai.lean.user.viewModel.User

class UserAdapter : BaseQuickAdapter<User, BaseViewHolder>(R.layout.item_user) {

    override fun convert(helper: BaseViewHolder, item: User) {
        val ivAvatar = helper.getView<ImageView>(R.id.ivAvatar)
        Glide.with(mContext).load(item.avatar).into(ivAvatar)
    }

}