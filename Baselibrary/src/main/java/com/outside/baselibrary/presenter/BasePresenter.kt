package com.outside.baselibrary.presenter

import android.content.Context
import com.outside.baselibrary.presenter.view.BaseView
import com.outside.baselibrary.utils.NetWorkUtils
import com.trello.rxlifecycle3.LifecycleProvider
import javax.inject.Inject

/**
 * className:    BasePresenter
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:15
 */
 
open class BasePresenter<T:BaseView> {
    lateinit var mView:T
    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var context: Context
    fun checkNetWork():Boolean{
        if(NetWorkUtils.isNetWorkAvailable(context)){
            return true
        }
        mView.onError("网络不可用")
        return false
    }
}