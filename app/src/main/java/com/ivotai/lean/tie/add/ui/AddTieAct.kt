package com.ivotai.lean.tie.add.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.View
import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.bumptech.glide.Glide
import com.ivotai.lean.R
import com.ivotai.lean.app.di.NetworkModule
import com.ivotai.lean.tie.add.useCase.AddTieUseCase
import com.ivotai.lean.upload.useCase.UploadPic
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_add_tie.*
import me.nereo.multi_image_selector.MultiImageSelector
import me.nereo.multi_image_selector.MultiImageSelectorActivity


class AddTieAct : AppCompatActivity() {

    private val code = 233

    private var pic = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_add_tie)

        // system ui
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        // ui
        RxView.clicks(ivPic)
//                .throttleFirst(500, TimeUnit.SECONDS)
                .subscribe { MultiImageSelector.create().start(this, code) }

        RxView.clicks(tvTitle).subscribe {
            val content = etContent.text.toString().trim()
            if (TextUtils.isEmpty(content)) {
                ToastUtils.showShort("写点话吧")
                return@subscribe
            }
            if (StringUtils.isEmpty(pic)) {
                ToastUtils.showShort("放张图吧")
                return@subscribe
            }
            AddTieUseCase(content, pic, Consumer {
                if (it.isSuccess()) {
                    finish()
                    ToastUtils.showShort("下拉刷新看看")
                }
                if (it.isError()) {
                    ToastUtils.showShort(it.error?.message)
                }
            })
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == code && resultCode == RESULT_OK) {
            val paths = data?.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT) ?: return
            UploadPic(paths[0], Consumer {
                if (it.isSuccess()) {
                    val result = it.data
                    if (result!!.success) {
                        Glide.with(this).load(NetworkModule.baseUrl + result.fileName).into(ivPic)
                        pic = result.fileName
                    }
                }
                if (it.isError()) {
                    ToastUtils.showShort(it.error?.message)
                }
            })
        }
    }

}