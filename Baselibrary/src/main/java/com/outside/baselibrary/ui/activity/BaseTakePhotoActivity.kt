package com.outside.baselibrary.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.outside.baselibrary.common.BaseApplication
import com.outside.baselibrary.injection.component.ActivityComponent
import com.outside.baselibrary.injection.component.DaggerActivityComponent
import com.outside.baselibrary.injection.module.ActivityModule
import com.outside.baselibrary.injection.module.LifeCycleProviderModule
import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.presenter.view.BaseView
import com.outside.baselibrary.utils.DateUtils
import com.outside.baselibrary.widgets.ProgressLoading
import org.jetbrains.anko.toast
import java.io.File
import javax.inject.Inject

/**
 * className:    BaseMvpActivity
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:17
 */

abstract class BaseTakePhotoActivity<T : BasePresenter<*>> : BaseActivity(), BaseView , TakePhoto.TakeResultListener {


    private lateinit var mTakePhoto: TakePhoto
    private lateinit var mTempFile: File


    override fun showLoading() {
        mLoadingDialog.showLoading()
    }

    override fun hideLoading() {
        mLoadingDialog.hideLoading()
    }

    override fun onError(text:String) {
        toast(text)
    }

    @Inject
    lateinit var mPresenter: T
    lateinit var activityComponent: ActivityComponent

    lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)

        initActivityInjection()
        injectComponent()
        mLoadingDialog = ProgressLoading.create(this)
        initView()
        initData()
    }

    abstract fun getLayoutId(): Int
    abstract fun injectComponent()
    abstract fun initView()
    open fun initData(){}

    private fun initActivityInjection() {

        println(this.applicationContext.toString())
        activityComponent = DaggerActivityComponent.builder()
            .appComponent((this.applicationContext as BaseApplication).appComponent)
            .activityModule(ActivityModule(this))
            .lifeCycleProviderModule(LifeCycleProviderModule(this))//生命周期
            .build()
    }

    fun showAlertView() {
        AlertView("选择图片", "", "取消", null, arrayOf("拍照", "相册"), this,
            AlertView.Style.ActionSheet, OnItemClickListener { _, position ->
                mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), true)
                when (position) {
                    0 -> {
                        createTempFile()
                        mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))

                    }
                    1 -> mTakePhoto.onPickFromGallery()
                    else -> {
                    }
                }
            }

        )
    }

    override fun takeSuccess(result: TResult?) {
        Log.d("TakePhoto", result?.image?.originalPath)
        Log.d("TakePhoto", result?.image?.compressPath)

    }

    override fun takeCancel() {
    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.d("TakePhoto", msg)
        msg?.let {
            toast(it)
        }
    }


    fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            mTempFile = File(Environment.getExternalStorageState(), tempFileName)
            return
        }
        mTempFile = File(filesDir, tempFileName)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }


}