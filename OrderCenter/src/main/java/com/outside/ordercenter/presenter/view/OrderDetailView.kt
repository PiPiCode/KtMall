package com.outside.ordercenter.presenter.view

import com.outside.baselibrary.presenter.view.BaseView
import com.outside.ordercenter.data.protocol.Order

/*
    订单详情页 视图回调
 */
interface OrderDetailView : BaseView {

    fun onGetOrderByIdResult(result: Order)
}
