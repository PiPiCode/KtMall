package com.outside.ordercenter.presenter

import com.kotlin.base.ext.excute
import com.kotlin.order.data.protocol.Order
import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.rx.BaseObserver
import com.outside.ordercenter.presenter.view.OrderListView
import com.outside.ordercenter.service.OrderService
import javax.inject.Inject

class OrderListPresenter @Inject constructor() : BasePresenter<OrderListView>() {

    @Inject
    lateinit var service: OrderService


    fun getOrderList(orderStatus: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        service.getOrderList(orderStatus).excute(object : BaseObserver<MutableList<Order>?>(mView) {
            override fun onNext(t: MutableList<Order>?) {

                mView.getOrderListResult(t)
            }
        }, lifecycleProvider)
    }


    fun confirmOrder(orderId: Int) {

        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        service.confirmOrder(orderId).excute(object : BaseObserver<Boolean>(mView) {
            override fun onNext(b: Boolean) {

                mView.confirmOrderResult(b)
            }
        }, lifecycleProvider)
    }

    fun cancelOrder(orderId: Int) {

        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        service.cancelOrder(orderId).excute(object : BaseObserver<Boolean>(mView) {
            override fun onNext(b: Boolean) {

                mView.cancelOrderResult(b)
            }
        }, lifecycleProvider)
    }

}
