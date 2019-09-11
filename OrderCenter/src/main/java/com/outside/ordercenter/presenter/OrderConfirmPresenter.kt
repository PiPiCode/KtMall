package com.outside.ordercenter.presenter

import com.outside.baselibrary.ext.excute
import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.rx.BaseObserver
import com.outside.ordercenter.data.protocol.GetOrderByIdReq
import com.outside.ordercenter.data.protocol.Order
import com.outside.ordercenter.presenter.view.OrderConfirmView
import com.outside.ordercenter.service.OrderService
import javax.inject.Inject

class OrderConfirmPresenter @Inject constructor() : BasePresenter<OrderConfirmView>() {

    @Inject
    lateinit var orderService: OrderService

    /*
        获取商品详情
     */
    fun getOrderById(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.getOrderById(GetOrderByIdReq(orderId))
            .excute(object : BaseObserver<Order>(mView) {
                override fun onNext(t: Order) {
                    if (t == null) {
                        mView.onError("数据解析错误")
                    } else {
                        mView.getOrderResult(t)
                    }
                }
            }, lifecycleProvider)
    }


    fun submitOrder(order: Order) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.submitOrder(order).excute(object : BaseObserver<Boolean>(mView) {
            override fun onNext(t: Boolean) {

                mView.submitOrderResult(t)
            }
        }, lifecycleProvider)
    }
}
