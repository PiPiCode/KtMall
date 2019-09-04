package com.outside.usercenter.ui.activity

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
import com.kotlin.base.ext.onClick
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.base.utils.DateUtils
import com.kotlin.base.utils.GlideUtils
import com.kotlin.provider.common.ProviderConstant
import com.kotlin.user.utils.UserPrefsUtils
import com.outside.baselibrary.common.BaseConstant
import com.outside.baselibrary.ui.activity.BaseMvpActivity
import com.outside.usercenter.R
import com.outside.usercenter.data.protocol.UserInfo
import com.outside.usercenter.injection.component.DaggerUserComponent
import com.outside.usercenter.injection.module.UserModule
import com.outside.usercenter.presenter.UserInfoPresenter
import com.outside.usercenter.presenter.view.UserInfoView
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import java.io.File

class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView,
    TakePhoto.TakeResultListener {


    private lateinit var mTakePhoto: TakePhoto
    private lateinit var mTempFile: File
    private val mUploadManager: UploadManager by lazy { UploadManager() }
    private var mLocalFilePath: String? = null
    private var mRemoteFilePath: String? = null

    private var mUserIcon: String? = null
    private var mUserName: String? = null
    private var mUserPhone: String? = null
    private var mUserGender: String? = null
    private var mUserSign: String? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_user_info
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)
    }

    /**
     * 初始化视图
     */
    override fun initView() {

        mUserIconView.onClick {
            showAlertView()
        }
        mHeaderBar.getRightView().onClick {
            mPresenter.updateUser(mRemoteFilePath!!,mUserNameEt.text?.toString()?:"",
                if(mGenderMaleRb.isChecked) "0" else "1",mUserSignEt.text?.toString()?:""
                )
        }
    }

    override fun initData() {
        //初始数据
        mUserIcon = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_NAME)
        mUserPhone = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_MOBILE)
        mUserGender = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_GENDER)
        mUserSign = AppPrefsUtils.getString(ProviderConstant.KEY_SP_USER_SIGN)

        mUserIcon?.let {
            mRemoteFilePath = it
            GlideUtils.loadImage(this, it, mUserIconIv)
        }
        mUserNameEt.setText(mUserName)
        mUserMobileTv.text = mUserPhone
        if (mUserGender == "0") mGenderMaleRb.isChecked = true
        else mGenderFemaleRb.isChecked = false
        mUserSignEt.setText(mUserSign)

    }

    private fun showAlertView() {
        AlertView("选择图片", "", "取消", null, arrayOf("拍照", "相册"), this,
            AlertView.Style.ActionSheet, OnItemClickListener { o, position ->
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
        mLocalFilePath = result?.image?.compressPath
        mPresenter.getUploadToken()
    }

    override fun takeCancel() {
    }

    override fun takeFail(result: TResult?, msg: String?) {
        Log.d("TakePhoto", msg)
    }


    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule())
            .build().inject(this)
        mPresenter.mView = this
    }


    override fun onGetUploadTokenResult(result: String) {

        mUploadManager.put(
            mLocalFilePath, "", result,
            { key, info, response ->
                mRemoteFilePath = BaseConstant.IMAGE_SERVER_ADDRESS + response.get("hash")
                GlideUtils.loadImage(UserInfoActivity@ this, mRemoteFilePath!!, mUserIconIv)
            }, null
        )
    }

    override fun onUpdateResult(result: UserInfo) {
        toast("修改成功")
        UserPrefsUtils.putUserInfo(result)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }


    fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            mTempFile = File(Environment.getExternalStorageState(), tempFileName)
            return
        }
        mTempFile = File(filesDir, tempFileName)
    }

}
