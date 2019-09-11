package com.outside.ordercenter.presenter

import com.outside.baselibrary.ext.excute
import com.outside.baselibrary.presenter.BasePresenter
import com.outside.baselibrary.rx.BaseObserver
import com.outside.ordercenter.data.protocol.GetOrderByIdReq
import com.outside.ordercenter.data.protocol.Order
import com.outside.ordercenter.presenter.view.OrderDetailView
import com.outside.ordercenter.service.OrderService
import javax.inject.Inject

/*
    订单详情页Presenter
 */
class OrderDetailPresenter @Inject constructor() : BasePresenter<OrderDetailView>() {

    @Inject
    lateinit var orderService: OrderService

    /*
        根据ID查询订单
     */
    fun getOrderById(orderId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        orderService.getOrderById(GetOrderByIdReq(orderId)).excute(object : BaseObserver<Order>(mView) {
            override fun onNext(t: Order) {
                    mView.onGetOrderByIdResult(t)
            }
        }, lifecycleProvider)

    }

}
