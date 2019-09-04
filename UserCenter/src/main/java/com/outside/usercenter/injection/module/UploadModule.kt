package com.outside.usercenter.injection.module

import com.outside.usercenter.service.UploadService
import com.outside.usercenter.service.impl.UploadServiceImpl
import dagger.Module
import dagger.Provides

/**
 * className:    UploadModule
 * description:  描述
 * author:       CLW2018
 * creatTime:    2019/9/2 18:24
 */
@Module
class UploadModule {

    @Provides
    fun providesUploadService(service:UploadServiceImpl):UploadService{
        return service
    }

}