package com.outside.usercenter.ui.activity

import android.view.View
import com.outside.baselibrary.ext.enable
import com.outside.baselibrary.ext.onClick
import com.outside.baselibrary.ui.activity.BaseMvpActivity
import com.outside.usercenter.R
import com.outside.usercenter.injection.component.DaggerUserComponent
import com.outside.usercenter.injection.module.UserModule
import com.outside.usercenter.presenter.ForgetPwdPresenter
import com.outside.usercenter.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {

    override fun getLayoutId(): Int {
        return R.layout.activity_forget_pwd
    }

    /**
     * 初始化视图
     */
    override fun initView() {


        mNextBtn.enable(mMobileEt) {isBtnEnable()}
        mNextBtn.enable(mVerifyCodeEt) {isBtnEnable()}
        mNextBtn.onClick(this)


    }

    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule())
            .build().inject(this)
        mPresenter.mView = this

    }


    override fun onClick(p0: View?) {
        p0?.let {
            when (it.id) {
                R.id.mVerifyCodeBtn -> {
                    mVerifyCodeBtn.requestSendVerifyNumber()
                    toast("发送验证码成功")
                }

                R.id.mNextBtn -> {
                    mPresenter.forgetPwd(
                        mMobileEt.text.toString(),
                        mVerifyCodeEt.text.toString()
                    )
                }

                else ->{

                }
            }
        }
    }


    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not()

    }
    override fun onForgetPwdResult(result: String) {
        toast(result)
        startActivity<ResetPwdActivity>("phone" to mMobileEt.text.toString())
    }
}
