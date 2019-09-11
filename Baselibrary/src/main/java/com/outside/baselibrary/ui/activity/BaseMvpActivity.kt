package com.outside.baselibrary.ui.activity

import android.os.Bundle
import com.outside.baselibrary.common.BaseApplication
import com.outside.baselibrary.injection.component.ActivityComponent
import com.outside.baselibrary.injection.component.DaggerActivityComponent
import com.outside.baselibrary.injection.module.ActivityModule
import com.outside.baselibrary.injection.module.LifeCycleProviderModule
import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.presenter.view.BaseView
import com.outside.baselibrary.widgets.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

/**
 * className:    BaseMvpActivity
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:17
 */

abstract class BaseMvpActivity<T : BasePresenter<*>> : BaseActivity(), BaseView {

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

}