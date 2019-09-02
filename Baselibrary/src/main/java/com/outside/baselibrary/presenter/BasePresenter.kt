package com.outside.baselibrary.presenter

import com.outside.baselibrary.presenter.view.BaseView

/**
 * className:    BasePresenter
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:15
 */
 
open class BasePresenter<T:BaseView> {
    lateinit var mView:T
}