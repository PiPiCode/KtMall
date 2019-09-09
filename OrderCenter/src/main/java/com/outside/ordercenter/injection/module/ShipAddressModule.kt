package com.outside.usercenter.injection.module

import com.kotlin.goods.service.impl.ShipAddressServiceImpl
import com.outside.ordercenter.service.ShipAddressService
import dagger.Module
import dagger.Provides

/**
 * className:    ShipAddressModule
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/9 15:29
 */
@Module
class ShipAddressModule {

    @Provides
    fun providesShipAddressService(service: ShipAddressServiceImpl): ShipAddressService {
        return service
    }

}