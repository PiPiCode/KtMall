package com.outside.goodscenter.presenter.view

import com.outside.baselibrary.presenter.view.BaseView
import com.outside.goodscenter.data.protocol.CartGoods

/**
 * className:    CartView
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:21
 */

interface CartView : BaseView {

    fun getCartList(result: MutableList<CartGoods>?)

    fun onDeleteCartList(b:Boolean)
    fun onSubmitCartListResult(t: Int)
}