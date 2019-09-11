package com.outside.usercenter.ui.activity

import android.util.Log
import com.jph.takephoto.model.TResult
import com.outside.baselibrary.common.BaseConstant
import com.outside.baselibrary.ext.onClick
import com.outside.baselibrary.ui.activity.BaseTakePhotoActivity
import com.outside.baselibrary.utils.AppPrefsUtils
import com.outside.baselibrary.utils.GlideUtils
import com.outside.provider.common.ProviderConstant
import com.outside.usercenter.R
import com.outside.usercenter.data.protocol.UserInfo
import com.outside.usercenter.injection.component.DaggerUserComponent
import com.outside.usercenter.injection.module.UserModule
import com.outside.usercenter.presenter.UserInfoPresenter
import com.outside.usercenter.presenter.view.UserInfoView
import com.outside.usercenter.utils.UserPrefsUtils
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast


class UserInfoActivity : BaseTakePhotoActivity<UserInfoPresenter>(), UserInfoView {


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


    /**
     * 初始化视图
     */
    override fun initView() {

        mUserIconView.onClick {
            showAlertView()
        }
        mHeaderBar.getRightView().onClick {
            mPresenter.updateUser(
                mRemoteFilePath!!, mUserNameEt.text?.toString() ?: "",
                if (mGenderMaleRb.isChecked) "0" else "1", mUserSignEt.text?.toString() ?: ""
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

    override fun takeSuccess(result: TResult?) {
        Log.d("TakePhoto", result?.image?.originalPath)
        Log.d("TakePhoto", result?.image?.compressPath)
        mLocalFilePath = result?.image?.compressPath
        mPresenter.getUploadToken()
    }


    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule())
            .build().inject(this)
        mPresenter.mView = this
    }


    override fun onGetUploadTokenResult(result: String) {

        mUploadManager.put(
            mLocalFilePath, "", result,
            { _, _, response ->
                mRemoteFilePath = BaseConstant.IMAGE_SERVER_ADDRESS + response.get("hash")
                GlideUtils.loadImage(UserInfoActivity@ this, mRemoteFilePath!!, mUserIconIv)
            }, null
        )
    }


    override fun onUpdateResult(result: UserInfo) {
        toast("修改成功")
        UserPrefsUtils.putUserInfo(result)
    }

}
