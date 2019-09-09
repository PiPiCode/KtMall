package com.kotlin.goods.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.base.ext.convertBoolean
import com.kotlin.goods.data.repository.OrderRepository
import com.kotlin.order.data.protocol.*
import com.kotlin.order.data.repository.ShipAddressRepository
import com.outside.ordercenter.service.OrderService
import com.outside.ordercenter.service.ShipAddressService
import io.reactivex.Observable
import javax.inject.Inject

/*
    购物车 业务层实现类
 */
class ShipAddressServiceImpl @Inject constructor(): ShipAddressService {

    @Inject
    lateinit var repository: ShipAddressRepository

    override fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<Boolean> {

        return repository.addShipAddress(shipUserName,shipUserMobile,shipAddress).convertBoolean()
    }

    override fun deleteShipAddress(req: DeleteShipAddressReq): Observable<Boolean> {
        return repository.deleteShipAddress(req.id).convertBoolean()

    }

    override fun editShipAddress(req: ShipAddress): Observable<Boolean> {
        return repository.editShipAddress(req).convertBoolean()
    }

    override fun getShipAddressList(): Observable<MutableList<ShipAddress>?> {
        return repository.getShipAddressList().convert()
    }





}
