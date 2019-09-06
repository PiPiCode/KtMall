package com.outside.goodscenter.presenter.view

import com.kotlin.goods.data.protocol.CartGoods
import com.kotlin.goods.data.protocol.Goods
import com.outside.baselibrary.presenter.view.BaseView

/**
 * className:    CartView
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:21
 */

interface CartView : BaseView {

    fun getCartList(result: MutableList<CartGoods>?)
}