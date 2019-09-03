package com.outside.usercenter.ui.activity

import android.view.View
import com.kotlin.base.ext.enable
import com.kotlin.base.ext.onClick
import com.outside.baselibrary.common.AppManager
import com.outside.baselibrary.ui.activity.BaseMvpActivity
import com.outside.usercenter.R
import com.outside.usercenter.injection.component.DaggerUserComponent
import com.outside.usercenter.injection.module.UserModule
import com.outside.usercenter.presenter.RegisterPresenter
import com.outside.usercenter.presenter.UserInfoPresenter
import com.outside.usercenter.presenter.view.RegisterView
import com.outside.usercenter.presenter.view.UserInfoView
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.mMobileEt
import kotlinx.android.synthetic.main.activity_register.mPwdEt
import org.jetbrains.anko.toast

class UserInfoActivity : BaseMvpActivity<UserInfoPresenter>(), UserInfoView {


    override fun getLayoutId(): Int {
        return R.layout.activity_user_info
    }

    /**
     * 初始化视图
     */
    override fun initView() {


    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule())
            .build().inject(this)
        mPresenter.mView = this

    }


}
