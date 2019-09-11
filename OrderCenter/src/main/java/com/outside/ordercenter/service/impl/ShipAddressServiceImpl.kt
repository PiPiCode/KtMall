package com.outside.ordercenter.service.impl

import com.outside.baselibrary.ext.convert
import com.outside.baselibrary.ext.convertBoolean
import com.outside.ordercenter.data.protocol.DeleteShipAddressReq
import com.outside.ordercenter.data.protocol.ShipAddress
import com.outside.ordercenter.data.repository.ShipAddressRepository
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
