package com.outside.ordercenter.injection.module

import com.outside.ordercenter.service.OrderService
import com.outside.ordercenter.service.impl.OrderServiceImpl
import dagger.Module
import dagger.Provides

/**
 * className:    OrderModule
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 18:24
 */
@Module
class OrderModule {

    @Provides
    fun providesOrderService(service: OrderServiceImpl): OrderService {
        return service
    }

}