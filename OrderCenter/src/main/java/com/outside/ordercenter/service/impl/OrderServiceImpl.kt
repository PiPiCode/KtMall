package com.kotlin.goods.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.base.ext.convertBoolean
import com.kotlin.goods.data.repository.OrderRepository
import com.kotlin.order.data.protocol.GetOrderByIdReq
import com.kotlin.order.data.protocol.Order
import com.outside.ordercenter.service.OrderService
import io.reactivex.Observable
import javax.inject.Inject

/*
    购物车 业务层实现类
 */
class OrderServiceImpl @Inject constructor(): OrderService {



    override fun submitOrder(order: Order): Observable<Boolean> {
        return repository.cancelOrder(order.id).convertBoolean()
    }


    @Inject
    lateinit var repository: OrderRepository



    override fun getOrderById(req: GetOrderByIdReq): Observable<Order> {
        return repository.getOrderById(req).convert()
    }


    override fun getOrderList(orderStatus: Int): Observable<MutableList<Order>?> {
        return repository.getOrderList(orderStatus).convert()
    }

    override fun confirmOrder(orderId: Int): Observable<Boolean> {
        return repository.confirmOrder(orderId).convertBoolean()
    }

    override fun cancelOrder(orderId: Int): Observable<Boolean> {
        return repository.cancelOrder(orderId).convertBoolean()
    }


}
