package com.outside.goodscenter.injection.module

import com.outside.goodscenter.service.CategoryService
import com.outside.goodscenter.service.impl.CategoryServiceImpl
import dagger.Module
import dagger.Provides

/**
 * className:    CategoryModule
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 18:24
 */
@Module
class CategoryModule {

    @Provides
    fun providesCategoryService(service:CategoryServiceImpl): CategoryService {
        return service
    }

}