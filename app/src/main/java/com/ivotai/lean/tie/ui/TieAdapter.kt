package com.ivotai.lean.tie.ui

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.ivotai.lean.R
import com.ivotai.lean.app.di.NetworkModule
import com.ivotai.lean.tie.po.Tie
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

class TieAdapter : BaseQuickAdapter<Tie, BaseViewHolder>(R.layout.item_tie) {

    override fun convert(helper: BaseViewHolder, tie: Tie) {
        val poster = tie.poster.target
        val ivAvatar = helper.getView<ImageView>(R.id.ivAvatar)
        Glide.with(mContext).load(NetworkModule.baseUrl + poster.avatar).into(ivAvatar)
        helper.getView<TextView>(R.id.tvName).text = poster.name


        helper.getView<TextView>(R.id.tvCreateTime).text = PrettyTime(Locale.CHINESE).format(tie.createTime).replace(" ", "")
        helper.getView<TextView>(R.id.tvContent).text = tie.content
        val ivPic = helper.getView<ImageView>(R.id.ivPic)
        Glide.with(mContext).load(
                if (tie.pic.startsWith("h")) tie.pic else NetworkModule.baseUrl + tie.pic
        ).into(ivPic)
    }

}