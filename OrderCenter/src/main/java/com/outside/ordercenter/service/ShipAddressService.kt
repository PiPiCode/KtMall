package com.outside.ordercenter.service

import com.kotlin.order.data.protocol.*
import io.reactivex.Observable

/**
 * className:    ShipAddressService
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/9 16:48
 */

interface ShipAddressService {

    /*
        添加收货地址
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<Boolean>

    /*
        删除收货地址
     */
    fun deleteShipAddress(req: DeleteShipAddressReq): Observable<Boolean>

    /*
        修改收货地址
     */
    fun editShipAddress(req: ShipAddress): Observable<Boolean>

    /*
        查询收货地址列表
     */
    fun getShipAddressList(): Observable<MutableList<ShipAddress>?>

}