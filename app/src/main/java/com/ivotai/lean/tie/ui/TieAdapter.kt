package com.ivotai.lean.tie.ui

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ivotai.lean.R
import com.ivotai.lean.tie.po.Tie

class TieAdapter : BaseQuickAdapter<Tie, BaseViewHolder>(R.layout.item_tie) {

    override fun convert(helper: BaseViewHolder, item: Tie) {
        val ivAvatar = helper.getView<ImageView>(R.id.ivAvatar)
        Glide.with(mContext).load(item.pic).into(ivAvatar)
    }

}