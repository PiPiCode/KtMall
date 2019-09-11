package com.outside.paysdk.data.repository


import javax.inject.Inject

import com.outside.baselibrary.data.net.RetrofitFactory
import com.outside.baselibrary.data.protocol.BaseResp
import com.outside.paysdk.data.api.PayApi
import com.outside.paysdk.data.protocol.GetPaySignReq
import com.outside.paysdk.data.protocol.PayOrderReq
import io.reactivex.Observable


/*
   支付数据层
 */
class PayRepository @Inject constructor() {

    /*
        获取支付宝支付签名
     */
    fun getPaySign(orderId: Int, totalPrice: Long): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(PayApi::class.java).getPaySign(GetPaySignReq(orderId, totalPrice))
    }

    /*
        刷新订单状态已支付
     */
    fun payOrder(orderId: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(PayApi::class.java).payOrder(PayOrderReq(orderId))
    }


}
