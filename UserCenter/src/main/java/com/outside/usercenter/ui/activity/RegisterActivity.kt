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
import com.outside.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.mMobileEt
import kotlinx.android.synthetic.main.activity_register.mPwdEt
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {



    override fun getLayoutId(): Int {
        return R.layout.activity_register
    }

    /**
     * 初始化视图
     */
    override fun initView() {

        mRegisterBtn.onClick(this)
        mVerifyCodeBtn.onClick(this)

        mRegisterBtn.enable(mMobileEt) {isBtnEnable()}
        mRegisterBtn.enable(mVerifyCodeEt) {isBtnEnable()}
        mRegisterBtn.enable(mPwdEt) {isBtnEnable()}
        mRegisterBtn.enable(mPwdConfirmEt) {isBtnEnable()}

    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule())
            .build().inject(this)
        mPresenter.mView = this

    }

    /**
     * 注册回调
     */
    override fun onRegisterResult(result: String) {
        toast(result)
    }


    override fun onClick(p0: View?) {
        p0?.let {
            when (it.id) {
                R.id.mVerifyCodeBtn -> {
                    mVerifyCodeBtn.requestSendVerifyNumber()
                    toast("发送验证码成功")
                }

                R.id.mRegisterBtn -> {
                    mPresenter.register(
                        mMobileEt.text.toString(),
                        mVerifyCodeEt.text.toString(),
                        mPwdEt.text.toString()
                    )
                }

                else ->{

                }
            }
        }
    }


    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()

    }
}
