package com.outside.usercenter.ui.activity

import android.view.View
import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.base.ext.enable
import com.kotlin.base.ext.onClick
import com.kotlin.user.utils.UserPrefsUtils
import com.outside.baselibrary.common.AppManager
import com.outside.baselibrary.ui.activity.BaseMvpActivity
import com.outside.provider.router.RouterPath
import com.outside.usercenter.R
import com.outside.usercenter.data.protocol.UserInfo
import com.outside.usercenter.injection.component.DaggerUserComponent
import com.outside.usercenter.injection.module.UserModule
import com.outside.usercenter.presenter.LoginPresenter
import com.outside.usercenter.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * className:    LoginActivity
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 17:00
 */
@Route(path = RouterPath.UserCenter.PATH_LOGIN)
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {

    private var pressTime: Long = 0L

    override fun getLayoutId(): Int {
        return R.layout.activity_login
    }

    /**
     * 初始化视图
     */
    override fun initView() {

        mLoginBtn.enable(mMobileEt) {isBtnEnable()}
        mLoginBtn.enable(mPwdEt) {isBtnEnable()}

        mLoginBtn.onClick(this)
        mForgetPwdTv.onClick(this)
        mHeaderBar.getRightView().onClick(this)
    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule())
            .build().inject(this)
        mPresenter.mView = this

    }

    override fun onLoginResult(userInfo: UserInfo) {
        toast("登录成功")
        UserPrefsUtils.putUserInfo(userInfo)
        startActivity<UserInfoActivity>()
        finish()

    }

    override fun onClick(p0: View?) {
        p0?.let {
            when (it.id) {
                R.id.mLoginBtn -> {

                    mPresenter.login(mMobileEt.text.toString(),mPwdEt.text.toString(),"")

                }

                R.id.mForgetPwdTv -> {
                    startActivity<ForgetPwdActivity>()

                }

                R.id.mRightTv -> {
                    startActivity<RegisterActivity>()
                }

                else ->{

                }
            }
        }
    }

    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not()
    }

    override fun onBackPressed() {
        val currentTime = System.currentTimeMillis()
        if (currentTime - pressTime > 2000) {
            toast("再按一次退出程序")
            pressTime = currentTime
        } else {
            AppManager.instance.exitApp(this)
        }
    }
}
