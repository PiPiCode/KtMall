package com.outside.paysdk.injection.module

import com.outside.paysdk.service.PayService
import com.outside.paysdk.service.impl.PayServiceImpl
import dagger.Module
import dagger.Provides

/**
 * className:    PayModule
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/10 11:16
 */

@Module
class PayModule {

    @Provides
    fun providesPayService(service:PayServiceImpl):PayService{
        return service
    }
}