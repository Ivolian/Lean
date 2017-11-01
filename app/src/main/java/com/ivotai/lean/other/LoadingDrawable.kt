package com.ivotai.lean.other

import android.content.res.Resources
import android.graphics.drawable.AnimationDrawable
import com.ivotai.lean.R

class LoadingDrawable(private val resources: Resources) : AnimationDrawable() {

    init {
        listOf(
                R.mipmap.gif_loading1,
                R.mipmap.gif_loading2,
                R.mipmap.gif_loading3,
                R.mipmap.gif_loading4,
                R.mipmap.gif_loading5,
                R.mipmap.gif_loading6,
                R.mipmap.gif_loading7
        ).forEach {
//            addFrame(resources.getDrawable(it), 100)
        }
    }

}