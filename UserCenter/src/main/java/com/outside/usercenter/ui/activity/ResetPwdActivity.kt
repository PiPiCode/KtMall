package com.outside.usercenter.ui.activity

import com.kotlin.base.ext.enable
import com.kotlin.base.ext.onClick
import com.outside.baselibrary.ui.activity.BaseMvpActivity
import com.outside.usercenter.R
import com.outside.usercenter.injection.component.DaggerUserComponent
import com.outside.usercenter.injection.module.UserModule
import com.outside.usercenter.presenter.ResetPwdPresenter
import com.outside.usercenter.presenter.view.ResetPwdView
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.*

class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdView {


    override fun getLayoutId(): Int {
        return R.layout.activity_reset_pwd
    }

    /**
     * 初始化视图
     */
    override fun initView() {

        mConfirmBtn.enable(mPwdEt) { isBtnEnable() }
        mConfirmBtn.enable(mPwdConfirmEt) { isBtnEnable() }
        mConfirmBtn.onClick{
            if(mPwdEt.text.toString() != mPwdConfirmEt.text.toString()){
                toast("密码不一致")
                return@onClick
            }
            mPresenter.resetPwd(
                intent.getStringExtra("phone"),
                mPwdEt.text.toString()
            )
        }
    }


    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule())
            .build().inject(this)
        mPresenter.mView = this

    }

    private fun isBtnEnable(): Boolean {
        return mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }

    override fun onResetPwdResult(result: String) {
        toast(result)
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
    }

}
