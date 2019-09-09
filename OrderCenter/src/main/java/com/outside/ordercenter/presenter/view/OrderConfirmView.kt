package com.outside.ordercenter.presenter.view

import com.kotlin.order.data.protocol.Order
import com.outside.baselibrary.presenter.view.BaseView

/**
 * className:    OrderConfirmView
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 16:21
 */

interface OrderConfirmView : BaseView {

    fun getOrderResult(result:Order)
    fun submitOrderResult(t: Boolean)

}