package com.outside.paysdk.service.impl

import com.outside.baselibrary.ext.convert
import com.outside.baselibrary.ext.convertBoolean
import com.outside.paysdk.data.repository.PayRepository
import com.outside.paysdk.service.PayService
import io.reactivex.Observable
import javax.inject.Inject

/**
 * className:    PayServiceImpl
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/10 11:10
 */

class PayServiceImpl @Inject  constructor():PayService  {

    @Inject
    lateinit var respository: PayRepository

    override fun getPaySign(orderId: Int, totalPrice: Long): Observable<String> {
        return respository.getPaySign(orderId,totalPrice).convert()
    }

    override fun payOrder(orderId: Int): Observable<Boolean> {
        return respository.payOrder(orderId).convertBoolean()
    }


}