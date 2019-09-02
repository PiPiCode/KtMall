package com.outside.baselibrary.ui.activity

import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.presenter.view.BaseView
import javax.inject.Inject

/**
 * className:    BaseMvpActivity
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:17
 */

open class BaseMvpActivity <T:BasePresenter<*>>: BaseActivity(),BaseView {

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError() {
    }

    @Inject
    lateinit var mPresenter:T

}