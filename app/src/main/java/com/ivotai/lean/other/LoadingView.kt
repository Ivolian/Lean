package com.ivotai.lean.other

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import com.ivotai.lean.R

class LoadingView : FrameLayout, LifecycleObserver {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
//    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    private var needRecover = false
    private lateinit var loadingDrawable: LoadingDrawable

    override fun onFinishInflate() {
        super.onFinishInflate()
        setBackgroundColor(Color.WHITE)
        inflate(context, R.layout.loading_view, this)
        loadingDrawable = LoadingDrawable(resources).apply {
            findViewById<ImageView>(R.id.ivLoading).setImageDrawable(this)
        }
        visibility = View.INVISIBLE
    }

    fun show() {
        loadingDrawable.start()
        visibility = View.VISIBLE
        needRecover = true
    }

    fun hide() {
        loadingDrawable.stop()
        visibility = View.INVISIBLE
        needRecover = false
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        if (needRecover) {
            loadingDrawable.start()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        loadingDrawable.stop()
    }

}
