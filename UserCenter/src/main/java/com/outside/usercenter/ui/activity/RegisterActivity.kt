package com.outside.usercenter.ui.activity

import android.os.Bundle
import com.kotlin.base.ext.onClick
import com.outside.baselibrary.ui.activity.BaseMvpActivity
import com.outside.usercenter.R
import com.outside.usercenter.presenter.RegisterPresenter
import com.outside.usercenter.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(),RegisterView {

    override fun onRegisterResult(b: Boolean) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mPresenter.mView = this

        mRegisterBtn.onClick {
            mPresenter.register(mMobileEt.text.toString(),mVerifyCodeEt.text.toString(),mPwdEt.text.toString())
        }
    }
}
