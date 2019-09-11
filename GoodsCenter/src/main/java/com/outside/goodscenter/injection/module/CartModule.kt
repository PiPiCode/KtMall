package com.outside.goodscenter.injection.module

import com.outside.goodscenter.service.CartService
import com.outside.goodscenter.service.impl.CartServiceImpl
import dagger.Module
import dagger.Provides

/**
 * className:    CartModule
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 18:24
 */
@Module
class CartModule {

    @Provides
    fun providesCartService(service: CartServiceImpl): CartService {
        return service
    }

}