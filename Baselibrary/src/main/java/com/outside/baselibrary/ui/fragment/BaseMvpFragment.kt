package com.outside.baselibrary.ui.fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.outside.baselibrary.common.BaseApplication
import com.outside.baselibrary.injection.component.ActivityComponent
import com.outside.baselibrary.injection.component.DaggerActivityComponent
import com.outside.baselibrary.injection.module.ActivityModule
import com.outside.baselibrary.injection.module.LifeCycleProviderModule
import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.presenter.view.BaseView
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

/**
 * className:    BaseMvpActivity
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:17
 */

abstract class BaseMvpFragment<T : BasePresenter<*>> : BaseFragment(), BaseView {

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun onError(text:String) {
        toast(text)
    }

    @Inject
    lateinit var mPresenter: T
    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        injectComponent()
    }

    abstract fun injectComponent()

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder()
            .appComponent((activity?.application as BaseApplication).appComponent)
            .activityModule(ActivityModule(activity as AppCompatActivity))
            .lifeCycleProviderModule(LifeCycleProviderModule(this))//生命周期
            .build()
    }

}