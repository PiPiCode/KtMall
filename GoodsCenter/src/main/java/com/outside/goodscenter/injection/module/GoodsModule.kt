package com.outside.usercenter.injection.module

import com.outside.goodscenter.service.GoodsService
import com.outside.goodscenter.service.impl.GoodsServiceImpl
import dagger.Module
import dagger.Provides

/**
 * className:    GoodsModule
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 18:24
 */
@Module
class GoodsModule {

    @Provides
    fun providesGoodsService(service:GoodsServiceImpl): GoodsService {
        return service
    }

}