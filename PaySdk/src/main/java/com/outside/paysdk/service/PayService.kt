package com.outside.paysdk.service

import io.reactivex.Observable

/**
 * className:    PayService
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/10 11:10
 */

interface PayService {


    //获取支付宝签名
    fun getPaySign(orderId: Int, totalPrice: Long): Observable<String>

    /*
    刷新订单状态已支付
    */
    fun payOrder(orderId: Int): Observable<Boolean>
}