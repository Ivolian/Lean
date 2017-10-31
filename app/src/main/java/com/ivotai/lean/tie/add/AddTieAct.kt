package com.ivotai.lean.tie.add

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.blankj.utilcode.util.ToastUtils
import com.ivotai.lean.R
import com.ivotai.lean.app.base.ViewState1
import com.ivotai.lean.app.di.ComponentsHolder
import com.ivotai.lean.tie.dto.TieWrapper
import com.ivotai.lean.tie.repo.TieRepo
import com.ivotai.lean.tie.ui.AddTieView
import com.ivotai.lean.upload.useCase.UploadPic
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.act_add_tie.*
import me.nereo.multi_image_selector.MultiImageSelector
import me.nereo.multi_image_selector.MultiImageSelectorActivity
import javax.inject.Inject


class AddTieAct : AppCompatActivity(), AddTieView {

    @Inject
    lateinit var tieRepo: TieRepo

    override fun backToTieView(state: ViewState1<TieWrapper>) {

        ToastUtils.showShort("ok")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_add_tie)

        // di
        ComponentsHolder.userComponent.inject(this)

        // system ui
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        // ui
        tvTitle.setOnClickListener {

            MultiImageSelector.create().start(this, 2333)

//            AddTie(this,etContent.text.toString(),tieRepo)

        }
    }

    private var pic = ""


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2333) {
            if (resultCode == Activity.RESULT_OK) {
                // 获取返回的图片列表
                val path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT)
                val path1 = path[0]
                UploadPic(path1, Consumer { ToastUtils.showShort(it.data?:it.error?.message) })
//                ToastUtils.showShort(pic)
            }
        }
    }


}