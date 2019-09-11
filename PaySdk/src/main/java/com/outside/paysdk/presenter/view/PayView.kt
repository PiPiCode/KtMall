package com.outside.paysdk.presenter.view

import com.outside.baselibrary.presenter.view.BaseView

/**
 * className:    PayView
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/10 11:13
 */

interface PayView :BaseView{
    fun getPaySignResult(t: String)
    fun payOrderResult(t: Boolean)
}