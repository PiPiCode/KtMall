package com.outside.ordercenter.service

import com.outside.ordercenter.data.protocol.GetOrderByIdReq
import com.outside.ordercenter.data.protocol.Order
import io.reactivex.Observable

/**
 * className:    OrderService
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/9 16:48
 */

interface OrderService {

    /*
        根据ID获取订单
     */
    fun getOrderById(req: GetOrderByIdReq): Observable<Order>


    fun submitOrder(order: Order): Observable<Boolean>


    fun getOrderList(orderStatus: Int): Observable<MutableList<Order>?>


    /*
      取消订单
   */
    fun cancelOrder(orderId: Int): Observable<Boolean>
    /*
        确认订单
     */
    fun confirmOrder(orderId: Int): Observable<Boolean>

}